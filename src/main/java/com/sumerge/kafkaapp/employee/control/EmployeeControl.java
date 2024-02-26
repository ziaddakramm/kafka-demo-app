package com.sumerge.kafkaapp.employee.control;


import com.sumerge.kafkaapp.common.exception.EmployeeNotFoundException;
import com.sumerge.kafkaapp.employee.entity.Employee;
import com.sumerge.kafkaapp.employee.repository.EmployeeRepository;
import com.sumerge.kafkaapp.sequence.repository.SequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static com.sumerge.kafkaapp.employee.entity.Employee.SEQUENCE_NAME;

@Component
public class EmployeeControl {

    private EmployeeRepository employeeRepository;
    private SequenceRepository sequenceRepository;

    @Autowired
    public EmployeeControl(EmployeeRepository employeeRepository, SequenceRepository sequenceRepository) {
        this.employeeRepository = employeeRepository;
        this.sequenceRepository = sequenceRepository;
    }


    public Employee checkFindById(Optional<Employee> result,int id) {
        Employee theEmployee = null;
        if (result.isPresent()) {
            theEmployee = result.get();
            return theEmployee;
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }

    public void setEmployeeId(Employee employee) {
        employee.setId(sequenceRepository.getSequenceNumber(SEQUENCE_NAME));
    }


    public void fieldsValidator(Employee employee) {
        Stream fieldsStream = Stream.of(employee.getFirstName(), employee.getLastName(), employee.getEmail());
        if (
                fieldsStream.anyMatch(value -> Objects.isNull(value) || Objects.equals(value, ""))) {
            throw new RuntimeException("The provided employee contained empty fields");
        }

    }

}
