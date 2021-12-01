package com.daffodil.ritikkanotra.ttassignment.service;

import com.daffodil.ritikkanotra.ttassignment.entity.Employee;
import com.daffodil.ritikkanotra.ttassignment.error.EmployeeNotFoundException;
import com.daffodil.ritikkanotra.ttassignment.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    private Employee theEmployee;

    @BeforeEach
    void setUp() {
        theEmployee =
                Employee.builder()
                        .employeeCode("APS-101")
                        .employeeName("Ritik kanotra")
                        .employeeDepartment("CSE")
                        .employeeEmail("ritik.kanotra@unthinkable.co")
                        .employeeDesignation("Software Engineer")
                        .build();

        Mockito.when(employeeRepository.findByEmployeeCode("APS-101"))
                .thenReturn(Optional.ofNullable(theEmployee));

        Employee updatedEmployee =
                Employee.builder()
                        .employeeCode("APS-101")
                        .employeeName("Ansh Bansal")
                        .employeeDepartment("CSE")
                        .employeeEmail("Ansh.Bansal@unthinkable.co")
                        .employeeDesignation("Software Engineer")
                        .build();

        Mockito.when(employeeRepository.save(updatedEmployee))
                .thenReturn(updatedEmployee);

        Mockito.when(employeeRepository.save(theEmployee))
                .thenReturn(theEmployee);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(theEmployee);

        Mockito.when(employeeRepository.findAll())
                .thenReturn(employeeList);
    }

    @Test
    @DisplayName("Get employee on VALID employee code")
    public void whenValidEmployeeCode_thenEmployeeShouldFound() throws EmployeeNotFoundException {
        String employeeCode = "APS-101";
        Employee found = employeeService.fetchEmployeeByEmployeeCode(employeeCode);
        assertEquals(employeeCode, found.getEmployeeCode());
    }


    @Test
    @DisplayName("Update employee on VALID employee code")
    public void whenEmployeeCodeFound_thenEmployeeShouldBeUpdated() throws EmployeeNotFoundException {
        String employeeCode = "APS-101";
        Employee employee =
                Employee.builder()
                        .employeeCode("APS-101")
                        .employeeName("Ansh Bansal")
                        .employeeDepartment("CSE")
                        .employeeEmail("Ansh.Bansal@unthinkable.co")
                        .employeeDesignation("Software Engineer")
                        .build();
        Employee updatedEmployee = employeeService.updateEmployeeByEmployeeCode(employeeCode, employee);
        assertEquals(employee.getEmployeeName(), updatedEmployee.getEmployeeName());
        assertEquals(employee.getEmployeeEmail(), updatedEmployee.getEmployeeEmail());
    }

    @Test
    @DisplayName("Check if new employee is created as expected")
    public void checkIfEmployeeIsCreatedCorrectly() {
        Employee createdEmployee = employeeService.saveEmployee(theEmployee);
        assertEquals(theEmployee.getEmployeeCode(), createdEmployee.getEmployeeCode());
        assertEquals(theEmployee.getEmployeeName(), createdEmployee.getEmployeeName());
        assertEquals(theEmployee.getEmployeeDepartment(), createdEmployee.getEmployeeDepartment());
        assertEquals(theEmployee.getEmployeeEmail(), createdEmployee.getEmployeeEmail());
        assertEquals(theEmployee.getEmployeeDesignation(), createdEmployee.getEmployeeDesignation());
    }

    @Test
    @DisplayName("Check if Employee List is returned as expected")
    public void checkIfEmployeeListIsReturnedCorrectly() {
        List<Employee> expectedEmployeeList = new ArrayList<>();
        expectedEmployeeList.add(theEmployee);
        List<Employee> actual = employeeService.fetchEmployeesList();
        assertEquals(expectedEmployeeList, actual);
    }




}