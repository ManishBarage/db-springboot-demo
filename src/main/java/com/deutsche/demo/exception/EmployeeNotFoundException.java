package com.deutsche.demo.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Integer id){
        super("Employee with the id "+id+" is not found!");
    }

    public EmployeeNotFoundException(String name){
        super("Employee with the id "+name+" is not found!");
    }

    public EmployeeNotFoundException(Double salary){
        super("Employee with the id "+salary+" is not found!");
    }
}
