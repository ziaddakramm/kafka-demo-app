package com.sumerge.kafkaapp.repository;

import com.sumerge.kafkaapp.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, Integer> {

}