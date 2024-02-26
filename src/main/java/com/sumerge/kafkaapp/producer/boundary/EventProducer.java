package com.sumerge.kafkaapp.producer.boundary;

import com.sumerge.kafkaapp.common.Constants;
import com.sumerge.kafkaapp.common.enums.OperationType;
import com.sumerge.kafkaapp.employee.entity.Employee;
import com.sumerge.kafkaapp.producer.AbstractProducer;
import com.sumerge.kafkaapp.common.entity.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EventProducer extends AbstractProducer<Event> {

    @Autowired
    public EventProducer(KafkaTemplate<String, Event> kafkaTemplate) {
        super(kafkaTemplate);
    }

    public void send(OperationType op, String user, int id, Employee employee) {
        sendMessage(new Event(user, op,id, employee), Constants.TOPIC_NAME,String.valueOf(id));
    }


}