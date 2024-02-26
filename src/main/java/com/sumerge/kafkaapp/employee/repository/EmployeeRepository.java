package com.sumerge.kafkaapp.employee.repository;

import com.sumerge.kafkaapp.employee.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, Integer> {

}