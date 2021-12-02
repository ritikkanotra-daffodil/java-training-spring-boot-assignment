package com.daffodil.ritikkanotra.ttassignment.controller;

import com.daffodil.ritikkanotra.ttassignment.entity.Employee;
import com.daffodil.ritikkanotra.ttassignment.error.EmployeeNotFoundException;
import com.daffodil.ritikkanotra.ttassignment.service.DepartmentService;
import com.daffodil.ritikkanotra.ttassignment.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeController employeeController;

    @MockBean
    private EmployeeService employeeService;

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
                    .employeeId(1L)
                    .build();

    }

    @Test
    @DisplayName("Save Employee Endpoint [Controller]")
    void saveEmployee() throws Exception {
        Employee employee =
                Employee.builder()
                        .employeeCode("APS-101")
                        .employeeName("Ritik kanotra")
                        .employeeDepartment("CSE")
                        .employeeEmail("ritik.kanotra@unthinkable.co")
                        .employeeDesignation("Software Engineer")
                        .build();

        Mockito.when(employeeService.saveEmployee(employee)).thenReturn(theEmployee);

        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(
                "{\n" +
                        "    \"employeeCode\":\"APS-101\",\n" +
                        "    \"employeeName\":\"Ritik kanotra\",\n" +
                        "    \"employeeDesignation\":\"Software Engineer\",\n" +
                        "    \"employeeDepartment\":\"CSE\",\n" +
                        "    \"employeeEmail\":\"ritik.kanotra@unthinkable.co\"\n" +
                        "}"
        )).andExpect(MockMvcResultMatchers.status().isOk());


    }

    @Test
    @DisplayName("Fetch employee list form database [Controller]")
    void fetchEmployeesList() throws Exception {

        Mockito.when(employeeService.fetchEmployeesList()).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders
                .get("/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @DisplayName("Fetch employee by employee code from database [Controller]")
    void fetchEmployeeByEmployeeCode() throws Exception {

        String employeeCode = "APS-101";
        Mockito.when(employeeService.fetchEmployeeByEmployeeCode(employeeCode)).thenReturn(theEmployee);
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/code/" + employeeCode))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @DisplayName("Remove employee by employee code from database[Controller]")
    void removeEmployeeByEmployeeCode() throws Exception {

        String employeeCode = "APS-101";
        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/code/" + employeeCode))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @DisplayName("Update employee from employee code [Controller]")
    void updateEmployeeByEmployeeCode() throws Exception {

        String employeeCode = "APS-101";

        Employee updatedEmployee = Employee.builder()
                .employeeCode("APS-101")
                .employeeName("Ritik kanotra")
                .employeeDepartment("CSE")
                .employeeEmail("ritik.kanotra@unthinkable.co")
                .employeeDesignation("Software Development Engineer")
                .build();

        Mockito.when(employeeService.updateEmployeeByEmployeeCode(employeeCode, updatedEmployee))
                .thenReturn(updatedEmployee);

        mockMvc.perform(MockMvcRequestBuilders.put("/employees/code/APS-101")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"employeeDesignation\":\"Software Development Engineer\"\n" +
                        "}")).andExpect(MockMvcResultMatchers.status().isOk());

    }
}