package com.sumerge.kafkaapp.boundary;


import com.sumerge.kafkaapp.entity.Employee;
import com.sumerge.kafkaapp.consumer.EmployeeConsumer;
import com.sumerge.kafkaapp.producer.EmployeeProducer;
import com.sumerge.kafkaapp.control.EmployeeControl;
import com.sumerge.kafkaapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/employees")
public class EmployeeResource {


    private EmployeeControl employeeControl;
    private EmployeeProducer employeeProducer;

    private EmployeeRepository employeeRepository;

    private EmployeeConsumer employeeConsumer;
    @Autowired

    public EmployeeResource(EmployeeControl employeeControl, EmployeeProducer employeeProducer, EmployeeRepository employeeRepository, EmployeeConsumer employeeConsumer) {
        this.employeeControl = employeeControl;
        this.employeeProducer = employeeProducer;
        this.employeeRepository = employeeRepository;
        this.employeeConsumer = employeeConsumer;
    }




    @GetMapping("/retrieve")
    public List<Employee> retrieveAll()
    {
        return employeeRepository.findAll();


    }

    @GetMapping("/retrieve/{id}")
    public Employee retrieveById(@PathVariable int id)
    {
        Optional<Employee> result=employeeRepository.findById(id);
        return employeeControl.checkFindById(result);
    }

    @PostMapping("/save")
    @Async
    public ResponseEntity<String> save(@RequestBody Employee employee)
    {
        //produce
        employeeProducer.sendMessage(employee,"createdEmployee");


        //consume
//        Employee consumedEmployee=employeeConsumer.getReceivedEmployee();
//
//        employeeControl.setEmployeeId(consumedEmployee);
//
//        employeeRepository.save(consumedEmployee);
//
        return ResponseEntity.ok("employee successfully saved");

    }


    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Employee employee)
    {
        //produce
        employeeProducer.sendMessage(employee,"updatedEmployee");


//        //consume
//        Employee consumedEmployee=employeeConsumer.getReceivedEmployee();
//
//        employeeRepository.save(consumedEmployee);
//
        return ResponseEntity.ok("employee successfully updated");
    }
}
