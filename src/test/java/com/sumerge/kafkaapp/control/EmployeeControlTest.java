package com.sumerge.kafkaapp.control;

import com.sumerge.kafkaapp.common.exception.EmployeeNotFoundException;
import com.sumerge.kafkaapp.employee.control.EmployeeControl;
import com.sumerge.kafkaapp.employee.entity.Employee;
import com.sumerge.kafkaapp.employee.repository.EmployeeRepository;
import com.sumerge.kafkaapp.sequence.repository.SequenceRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.Assert.*;

public class EmployeeControlTest {
    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private SequenceRepository sequenceRepository;

    private EmployeeControl underTest;

    Employee testEmployee1=new Employee(
            "ziad",
            "akram",
            "za@g.com"
    );

    @Before
    public void setUp() throws Exception {

        underTest = new EmployeeControl(employeeRepository,sequenceRepository);
    }

    @Test
    public void checkFindByIdTrue() {
        testEmployee1.setId(1);
        Optional<Employee> mockResult = Optional.of(testEmployee1);

        Employee result = underTest.checkFindById(mockResult,1);

        assertEquals(testEmployee1, result);

    }

    @Test
    public void checkFindByIdThrowsException() {

        Optional<Employee> mockResult = Optional.empty();



        EmployeeNotFoundException exception = assertThrows(EmployeeNotFoundException.class,
                () -> underTest.checkFindById(mockResult,1));

        assertEquals(1, exception.getId());
    }


    @Test
    public void checkFieldsValidatorTest() {

         underTest.fieldsValidator(testEmployee1);
    }

    @Test
    public void checkFieldsValidatorThrowsException() {

        Employee testEmployee2=new Employee(
                "",
                "akram",
                "za@g.com"
        );


        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> underTest.fieldsValidator(testEmployee2));

        assertEquals("The provided employee contained empty fields", exception.getMessage());
    }




}