package com.deutsche.demo.controller;

import ch.qos.logback.core.joran.spi.EventPlayer;
import com.deutsche.demo.model.Employee;
import com.deutsche.demo.service.EmployeeService;
import io.swagger.v3.oas.annotations.headers.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// methods in this class should not return raw business objects
// they should return ResponseEntity objects
// refactor the code accordingly
// LOG as applicable
// access ui here -
//http://localhost:8090/api/v1/swagger-ui/index.html

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("employees")
public class EmployeeController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeService employeeService;

    //    http://localhost:8090/api/v1/employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    //    http://localhost:8090/api/v1/employees/101
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(name = "id") Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Employee with the id "+ id+ "returned successfully");
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<Employee> response = new ResponseEntity<>(employee, headers, status);
        return response;
    }


    // http://localhost:8090/api/v1/employees/name/{name}
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Employee>> getEmployeeByName(@PathVariable(name = "name") String name) {
        List<Employee> employee = employeeService.getEmployeeByName(name);
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Employee with the name " + name + " returned successfully");
        return new ResponseEntity<>(employee, headers, HttpStatus.OK);
    }


    @GetMapping("/salary/{salary}")
    public ResponseEntity<List<Employee>> getEmployeeByName(@PathVariable(name = "salary") Double salary) {
        List<Employee> employee = employeeService.getEmployeeBySalary(salary);
        HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Employee with the name " + salary + " returned successfully");
        return new ResponseEntity<>(employee, headers, HttpStatus.OK);
    }

    //    http://localhost:8090/api/v1/employees
    @PostMapping
    public ResponseEntity<?> addEmployee(@Valid @RequestBody Employee employee) {
        try {
            Employee savedEmployee = employeeService.addEmployee(employee);
            HttpHeaders headers = new HttpHeaders();
            headers.add("message", "Employee added successfully");
            return new ResponseEntity<>(savedEmployee, headers, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding employee: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //    http://localhost:8090/api/v1/employees
    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        HttpHeaders headers = new HttpHeaders();
        headers.add("message","Employee updated successfully");
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<Employee> response = new ResponseEntity<>(updatedEmployee, headers,status);
        return response;
    }

    //    http://localhost:8090/api/v1/employees/101
    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable(name = "id") Integer id) {
        Employee deletedEmployee = employeeService.deleteEmployee(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("message","Employee deleted successfully");
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<Employee> response = new ResponseEntity<>(deletedEmployee, headers,status);
        return response;
    }
}
