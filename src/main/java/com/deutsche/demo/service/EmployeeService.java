package com.deutsche.demo.service;

import com.deutsche.demo.exception.EmployeeNotFoundException;
import com.deutsche.demo.model.Employee;
import com.deutsche.demo.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Write business logic in this class
// getEmployeeById() - What if the employee does not exist?
// deleteEmployee() - What if the employee does not exist?
// addEmployee() - What if the employee already exists?
// etc

@Service
public class EmployeeService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        LOG.info("Fetching all employees");
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        LOG.info("Fetching employee by ID: {}", id);
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if(employeeOptional.isPresent()){
            return employeeOptional.get();
        }
        else{
            throw new EmployeeNotFoundException(id);
        }
    }
    public List<Employee> getEmployeeByName(String name){
        LOG.info("Fetching employee by name: {}", name);
        List<Employee> employee = employeeRepository.findByName(name);

        if(employee.isEmpty()){
            throw new EmployeeNotFoundException(name);
        }
        return employee;
    }

    public List<Employee> getEmployeeBySalary(Double salary){
        LOG.info("Fetching employee by name: {}", salary);
        List<Employee> employee = employeeRepository.findBySalary(salary);

        if(employee.isEmpty()){
            throw new EmployeeNotFoundException(salary);
        }
        return employee;
    }

    public Employee addEmployee(Employee employee) {
        LOG.info("Attempting to add employee: {}", employee);

        // NEW: Check if ID is not null and already exists
        if (employee.getId() != null) {
            Optional<Employee> existingEmployee = employeeRepository.findById(employee.getId());
            if(existingEmployee.isPresent()){
                throw new RuntimeException("Employee with id " + employee.getId() + " already exists!");
            }
        }

        return employeeRepository.save(employee);
    }


    public Employee updateEmployee(Employee employee) {
        LOG.info("Attempting to update employee: {}", employee);
        Optional<Employee> existingEmployee = employeeRepository.findById(employee.getId());

        if(existingEmployee.isPresent()){
            return employeeRepository.save(employee);
        }
        else{
            throw new EmployeeNotFoundException(employee.getId());
        }
    }

    public Employee deleteEmployee(Integer id) {
        LOG.info("Attempting to delete employee with id: {}", id);
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if(optionalEmployee.isEmpty()){
            throw new EmployeeNotFoundException(id);
        }
        else{
            employeeRepository.deleteById(id);
            return optionalEmployee.get();
        }
    }
}
