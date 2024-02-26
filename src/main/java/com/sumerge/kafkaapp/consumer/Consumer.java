package com.sumerge.kafkaapp.consumer;


import com.sumerge.kafkaapp.common.Constants;
import com.sumerge.kafkaapp.employee.repository.EmployeeRepository;
import com.sumerge.kafkaapp.common.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class Consumer {

    private EmployeeRepository employeeRepository;

    @Autowired
    public Consumer(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }



    @KafkaListener(topics = Constants.TOPIC_NAME)
    public void consume(Event event) {

        switch (event.operationType) {
            case CREATE:
                employeeRepository.save(employeeRepository.save(event.entity));
                break;
            case UPDATE:
                employeeRepository.save(employeeRepository.save(event.entity));
                break;
            default:
                break;
        }

    }
}
