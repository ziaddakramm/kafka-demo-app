package com.sumerge.kafkaapp.controller;


import com.sumerge.kafkaapp.entity.Employee;
import com.sumerge.kafkaapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController {


    private EmployeeService employeeService;
    private EmployeeProducer employeeProducer;
    @Autowired

    public EmployeeController(EmployeeService employeeService, EmployeeProducer employeeProducer) {
        this.employeeService = employeeService;
        this.employeeProducer = employeeProducer;
    }




    @GetMapping("/retrieve")
    public List<Employee> retrieveAll()
    {
        return employeeService.findAll();
    }

    @GetMapping("/retrieve/{id}")
    public Employee retrieveAll(@PathVariable int id)
    {
        return employeeService.findById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Employee employee)
    {
        employeeProducer.sendMessage(employee);
        return ResponseEntity.ok("employee successfully saved");
    }


    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Employee employee)
    {
        employeeService.update(employee);
        return ResponseEntity.ok("employee successfully updated");
    }
}
