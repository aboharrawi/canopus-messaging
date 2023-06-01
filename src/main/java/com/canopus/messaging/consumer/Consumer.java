package com.canopus.messaging.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "canopus-live-stream", groupId = "canopus-streaming", topicPartitions = @TopicPartition(topic = "canopus-live-stream", partitions = "0"))
    public void consume(Message<Map<String, String>> message) throws Exception {
        logger.info(String.format("Received message c1 %s", message));
        appendToPrimaryFile(Paths.get(message.getPayload().get("location")));
    }

    private void appendToPrimaryFile(Path path) throws Exception {
        Path mergePath = path.getParent().resolve("merge.webm");
        if (mergePath.toFile().exists()) {
            write(path, mergePath);
        } else {
            Files.copy(path, mergePath);
        }
    }

    private void write(Path from, Path to) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(from.toFile());
             FileOutputStream outputStream = new FileOutputStream(to.toFile(), true);
             FileChannel inChannel = inputStream.getChannel();
             FileChannel outChannel = outputStream.getChannel()) {
            outChannel.position(outChannel.size());
            inChannel.transferTo(0, inChannel.size(), outChannel);
        }
    }
}