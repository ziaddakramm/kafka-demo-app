package com.sumerge.kafkaapp.service;


import com.sumerge.kafkaapp.entity.Employee;
import com.sumerge.kafkaapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.sumerge.kafkaapp.entity.Employee.SEQUENCE_NAME;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private SequenceGeneratorService seqGeneratorService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, SequenceGeneratorService seqGeneratorService) {
        this.employeeRepository = employeeRepository;
        this.seqGeneratorService = seqGeneratorService;
    }

    public List<Employee> findAll()
    {
        return employeeRepository.findAll();
    }
    public Employee findById(int id)
    {
        Optional<Employee> result=employeeRepository.findById(id);

        Employee theEmployee=null;
        if(result.isPresent())
        {
            theEmployee=result.get();
            return theEmployee;
        }
        else
        {
            throw new RuntimeException("Did not find employee with id - "+ theEmployee.getId());
        }
    }


    @KafkaListener(topics="createdEmployee",groupId = "demoGroup")
    public void save(Employee employee)
    {
        employee.setId(seqGeneratorService.getSequenceNumber(SEQUENCE_NAME));
        employeeRepository.save(employee);
    }




    public void update(Employee employee)
    {
       Employee tempEmployee = findById(employee.getId());
       employeeRepository.save(employee);
    }

}
