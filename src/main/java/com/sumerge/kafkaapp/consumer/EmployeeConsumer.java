package com.sumerge.kafkaapp.consumer;


import com.sumerge.kafkaapp.employee.control.EmployeeControl;
import com.sumerge.kafkaapp.employee.entity.Employee;
import com.sumerge.kafkaapp.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConsumer {
//
//    private EmployeeRepository employeeRepository;
//    private EmployeeControl employeeControl;
//
//    @Autowired
//    public EmployeeConsumer(EmployeeRepository employeeRepository, EmployeeControl employeeControl) {
//        this.employeeRepository = employeeRepository;
//        this.employeeControl = employeeControl;
//    }
//
//
//
//    @KafkaListener(topics="createdEmployee",groupId = "demoGroup")
//    public void consumeCreatedEmployee(Employee employee)
//    {
//        employeeRepository.save(employee);
//    }
//
//    @KafkaListener(topics="updatedEmployee",groupId = "demoGroup")
//    public void consumeUpdatedEmployee(Employee employee)
//    {
//
//        employeeRepository.save(employee);
//    }

}
