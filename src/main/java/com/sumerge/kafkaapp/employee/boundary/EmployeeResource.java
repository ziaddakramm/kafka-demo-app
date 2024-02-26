package com.sumerge.kafkaapp.employee.boundary;


import com.sumerge.kafkaapp.common.enums.OperationType;
import com.sumerge.kafkaapp.employee.entity.Employee;

import com.sumerge.kafkaapp.employee.control.EmployeeControl;
import com.sumerge.kafkaapp.employee.repository.EmployeeRepository;
import com.sumerge.kafkaapp.producer.boundary.EventProducer;
import com.sumerge.kafkaapp.service.ObjectModel;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/employees")
public class EmployeeResource {


    private EmployeeControl employeeControl;


    private EventProducer eventProducer;

    private EmployeeRepository employeeRepository;

    private String topicId="topicId";
    @Autowired
    public EmployeeResource(EmployeeControl employeeControl, EventProducer eventProducer, EmployeeRepository employeeRepository) {
        this.employeeControl = employeeControl;
        this.eventProducer = eventProducer;
        this.employeeRepository = employeeRepository;
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
        return employeeControl.checkFindById(result,id);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody @NonNull Employee employee)
    {
        employeeControl.fieldsValidator(employee);
        employeeControl.setEmployeeId(employee);
        eventProducer.send(OperationType.CREATE,"user", employee.getId(), employee);
        return ResponseEntity.ok("employee successfully saved");

    }


    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody @NonNull Employee employee)
    {

        Optional<Employee> result=employeeRepository.findById(employee.getId());

        employeeControl.checkFindById(result,employee.getId());

        eventProducer.send(OperationType.UPDATE,"user", employee.getId(), employee);

        return ResponseEntity.ok("employee successfully updated");
    }
}
