package com.sumerge.kafkaapp.consumer;


import com.sumerge.kafkaapp.control.EmployeeControl;
import com.sumerge.kafkaapp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static com.sumerge.kafkaapp.entity.Employee.SEQUENCE_NAME;

@Component
public class EmployeeConsumer {

    private EmployeeControl employeeControl;

    private Employee receivedEmployee;

    public EmployeeControl getEmployeeControl() {
        return employeeControl;
    }

    public void setEmployeeControl(EmployeeControl employeeControl) {
        this.employeeControl = employeeControl;
    }

    public Employee getReceivedEmployee() {
        return receivedEmployee;
    }

    public void setReceivedEmployee(Employee receivedEmployee) {
        this.receivedEmployee = receivedEmployee;
    }

    @Autowired
    public EmployeeConsumer(EmployeeControl employeeControl) {
        this.employeeControl = employeeControl;
    }

    @KafkaListener(topics="createdEmployee",groupId = "demoGroup")
    public void consumeCreatedEmployee(Employee employee)
    {
//        setReceivedEmployee(employee);
        employeeControl.save(employee);
    }

    @KafkaListener(topics="updatedEmployee",groupId = "demoGroup")
    public void consumeUpdatedEmployee(Employee employee)
    {
//        setReceivedEmployee(employee);
        employeeControl.update(employee);
    }

}
