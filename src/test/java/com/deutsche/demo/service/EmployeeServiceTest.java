package com.deutsche.demo.service;

import com.deutsche.demo.model.Employee;
import com.deutsche.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void testGetAllEmployees() {
        List<Employee> mockEmployees = List.of(new Employee(10, "IT", 45000.00));
        when(employeeRepository.findAll()).thenReturn(mockEmployees);

        List<Employee> allEmployees = employeeService.getAllEmployees();
        assertFalse(allEmployees.isEmpty());
    }
}


//package com.deutsche.demo.service;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//
//public class EmployeeServiceTest {
//
//    private static EmployeeService employeeService;
//
//    @BeforeAll
//    public static void setUp() {
//        employeeService = new EmployeeService();
//    }
//
//    @Test
//    public void testGetAllEmployees() {
//        assertFalse(employeeService.getAllEmployees().isEmpty(), "Employee list should not be empty");
//    }
//}