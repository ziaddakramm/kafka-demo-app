package com.sumerge.kafkaapp.control;


import com.sumerge.kafkaapp.entity.Employee;
import com.sumerge.kafkaapp.repository.EmployeeRepository;
import com.sumerge.kafkaapp.repository.SequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.sumerge.kafkaapp.entity.Employee.SEQUENCE_NAME;

@Component
public class EmployeeControl {

    private EmployeeRepository employeeRepository;
    private SequenceRepository sequenceRepository;

    @Autowired
    public EmployeeControl(EmployeeRepository employeeRepository, SequenceRepository sequenceRepository) {
        this.employeeRepository = employeeRepository;
        this.sequenceRepository = sequenceRepository;
    }



    public Employee checkFindById(Optional<Employee> result)
    {
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

    public void setEmployeeId(Employee employee)
    {
        employee.setId(sequenceRepository.getSequenceNumber(SEQUENCE_NAME));
    }

    public void save(Employee employee)
    {
        setEmployeeId(employee);
        employeeRepository.save(employee);
    }


    public void update(Employee employee)
    {
       employeeRepository.save(employee);
    }

}
