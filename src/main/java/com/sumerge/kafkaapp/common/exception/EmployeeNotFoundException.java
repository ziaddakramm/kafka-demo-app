package com.sumerge.kafkaapp.common.exception;

public class EmployeeNotFoundException  extends RuntimeException{

    private int id;


    public EmployeeNotFoundException(String message) {
        super(message);
    }


    public EmployeeNotFoundException(int id)
    {
        this.id=id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
