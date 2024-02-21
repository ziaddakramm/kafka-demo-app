package com.sumerge.kafkaapp.configuration.kafka;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class kafkaTopicConfig {

    @Bean
    public NewTopic createdEmplTopic() {
        return TopicBuilder.name("createdEmployee")
                .build();
    }


    @Bean
    public NewTopic updatedEmplTopic() {
        return TopicBuilder.name("updatedEmployee")
                .build();
    }


}
