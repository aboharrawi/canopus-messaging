package com.canopus.messaging.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "canopus-live-stream", groupId = "canopus-streaming")
    public void consume(Message<Map<String, String>> message) {
        logger.info(String.format("Received message %s", message));
    }
}