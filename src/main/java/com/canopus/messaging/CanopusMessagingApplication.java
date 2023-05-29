package com.canopus.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableDiscoveryClient(autoRegister = false)
public class CanopusMessagingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CanopusMessagingApplication.class, args);
    }

}
