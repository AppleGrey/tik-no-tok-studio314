package com.studio314.recommendationservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendStartMessage(String message) {
        kafkaTemplate.send("video-start",message);
    }

    public void sendAccomplishMessage(String message) {
        kafkaTemplate.send("video-accomplish", message);
    }

}
