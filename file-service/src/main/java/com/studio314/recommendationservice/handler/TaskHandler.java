package com.studio314.recommendationservice.handler;


import com.alibaba.fastjson2.JSON;
import com.studio314.recommendationservice.kafka.MessageProducer;
import com.studio314.tiknotokcommon.dto.VideoMsgDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TaskHandler {

    @Autowired
    MessageProducer messageProducer;

    public void handleMessage(String message) {
//        messageProducer.sendStartMessage(message);
        VideoMsgDTO videoMsg = JSON.parseObject(message, VideoMsgDTO.class);
        System.out.println("TaskStart: " + message);
        // todo: 处理视频，生成封面和链接
        String vUrl = null;
        String coverUrl = null;

        videoMsg.setVUrl(vUrl);
        videoMsg.setCoverUrl(coverUrl);
        String accomplishMessage = JSON.toJSONString(videoMsg);
        messageProducer.sendAccomplishMessage(accomplishMessage);
        System.out.println("TaskAccomplish: " + accomplishMessage);
    }

}
