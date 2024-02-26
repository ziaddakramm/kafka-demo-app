package com.sumerge.kafkaapp.common.entity;


import com.sumerge.kafkaapp.common.enums.OperationType;
import com.sumerge.kafkaapp.employee.entity.Employee;
import lombok.Data;

@Data
public class Event extends AbstractEvent<Employee> {
    public Event() {
        super();
    }

    public Event(String user, OperationType operationType, int id, Employee entity) {
        super(user, operationType, id, entity);
    }



    @Override
    public String toString() {
        return super.toString();
    }
}
