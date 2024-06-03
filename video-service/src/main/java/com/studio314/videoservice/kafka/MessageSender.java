package com.studio314.videoservice.kafka;

import com.alibaba.fastjson2.JSON;
import com.studio314.tiknotokcommon.dto.VideoMsgDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void addWaitMission(VideoMsgDTO mission) {
        //将m用fastjson转成json文本
        String message = JSON.toJSONString(mission);

        kafkaTemplate.send("video-wait", message);
    }

}
