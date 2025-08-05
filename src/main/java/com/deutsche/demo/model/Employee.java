package com.deutsche.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


@Entity // mandotory
@Table(name = "employees") // optional, but conditional
public class Employee {

    @Id // mandotory
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Name should be provided")
    @NotBlank(message = "name cannot be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 to 50 characters")
    @Column(name = "name")
    private String name;

    @Positive(message = "Salary should be more than 0")
    @Column(name = "salary")
    private Double salary;

    public Employee() {

    }

    public Employee(Integer id, String name, Double salary) {
        System.out.println("asdf");
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name='" + name + '\'' + ", salary=" + salary + '}';
    }
}
