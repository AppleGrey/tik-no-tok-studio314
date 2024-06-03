package com.studio314.videoservice.kafka;

import com.alibaba.fastjson2.JSON;
import com.studio314.tiknotokcommon.dto.VideoMsgDTO;
import com.studio314.videoservice.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @Autowired
    VideoService videoService;

    @KafkaListener(topics = "video-accomplish", groupId = "testGroup")
    public void listenAccomplish(String message) {
        VideoMsgDTO videoMsg = JSON.parseObject(message, VideoMsgDTO.class);
        videoService.updateVideo(videoMsg);
    }

}
