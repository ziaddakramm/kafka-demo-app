package com.sumerge.kafkaapp.controller;


import com.sumerge.kafkaapp.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeProducer {

    private static final Logger LOGGER= LoggerFactory.getLogger(EmployeeProducer.class);

    private KafkaTemplate<String, Employee> kafkaTemplate;

    public EmployeeProducer(KafkaTemplate<String, Employee> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendMessage(Employee employee){
        LOGGER.info(String.format(" JSON Message send %s",employee.toString()));


        Message<Employee> message= MessageBuilder
                .withPayload(employee)
                .setHeader(KafkaHeaders.TOPIC,"createdEmployee")
                .build();

        kafkaTemplate.send(message);
    }
}
