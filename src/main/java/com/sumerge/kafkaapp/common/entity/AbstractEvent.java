package com.sumerge.kafkaapp.common.entity;

import com.sumerge.kafkaapp.common.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbstractEvent<T> implements Serializable {

    public String user;
    public OperationType operationType;
    public int id;

    public T entity;

}
