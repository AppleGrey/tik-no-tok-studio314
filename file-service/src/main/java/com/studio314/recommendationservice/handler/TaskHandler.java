package com.studio314.recommendationservice.handler;


import com.alibaba.fastjson2.JSON;
import com.studio314.recommendationservice.domain.pojo.VideoTmp;
import com.studio314.recommendationservice.kafka.MessageProducer;
import com.studio314.recommendationservice.mapper.VideoMapper;
import com.studio314.recommendationservice.utils.GetVideoGainImg;
import com.studio314.recommendationservice.utils.VideoUtils;
import com.studio314.tiknotokcommon.dto.VideoMsgDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class TaskHandler {

    @Value("${me.file.upload-path}")
    private String uploadDir;

    @Autowired
    MessageProducer messageProducer;

    @Autowired
    VideoMapper videoMapper;

    public void handleMessage(String message) {
//        messageProducer.sendStartMessage(message);
        VideoMsgDTO videoMsg = JSON.parseObject(message, VideoMsgDTO.class);
        System.out.println("TaskStart: " + message);
        VideoTmp tmpVideo = videoMapper.getTmpVideo(videoMsg.getUuid());
        videoMapper.updateVID(videoMsg.getUuid(), videoMsg.getVID());
        String filePath = uploadDir + tmpVideo.getPath();
        String coverPath = uploadDir + tmpVideo.getUUID() + ".jpg";
        String compressPath = uploadDir + tmpVideo.getUUID() + "_compress.mp4";
        try {
            boolean success = GetVideoGainImg.getTempPath(coverPath, filePath);
            if (!success) {
                return;
            }
        } catch (Exception e) {
            return;
        }
        String vUrl = "/file/source/" + tmpVideo.getPath();
        String coverUrl = "/file/source/" + tmpVideo.getUUID() + ".jpg";

        // 转码
        VideoUtils.compressVideo(filePath, compressPath, 500, 25);

        videoMapper.updateCompress(videoMsg.getUuid(), tmpVideo.getUUID() + "_compress.mp4");

        videoMsg.setVUrl(vUrl);
        videoMsg.setCoverUrl(coverUrl);
        String accomplishMessage = JSON.toJSONString(videoMsg);
        messageProducer.sendAccomplishMessage(accomplishMessage);
        System.out.println("TaskAccomplish: " + accomplishMessage);
    }

}
