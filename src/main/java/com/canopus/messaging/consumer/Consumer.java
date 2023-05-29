package com.canopus.messaging.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "canopus-live-stream", groupId = "canopus-streaming")
    public void consume(byte[] message) {
        logger.info(String.format("Received byte[] message with length %d", message.length));
    }
}