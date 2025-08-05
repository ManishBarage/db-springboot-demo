package com.deutsche.demo.service;

import com.deutsche.demo.model.Employee;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmployeeServiceTest {
    @Autowired
    private static EmployeeService service = new EmployeeService();
    private static List<Employee> empList;

    @BeforeAll
    static void setUp() {
        empList = service.getAllEmployees();
    }
//
//    @AfterAll
//    static void tearDown() {
//        service = null;
//    }

    @Test
    void testFetchAllEmployeesSize() {
        assertEquals(6, empList.size());
    }

//    @Test
//    void testFetchAllEmployeesSizePositive() {
//        assertNotEquals(0, empList.size());
//    }
//
////    @Disabled
//    @Test
//    void testFetchAllEmployeesNotNull() {
//        assertNotNull(empList);
//    }
//
//    @Test
//    void testFetchAllEmployeesNotEmpty() {
//        assertFalse(empList.isEmpty());
//    }


}