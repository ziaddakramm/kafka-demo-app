package com.sumerge.kafkaapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication

public class KafkaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaAppApplication.class, args);
	}

}
