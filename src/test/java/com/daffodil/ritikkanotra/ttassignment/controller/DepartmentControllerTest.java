package com.daffodil.ritikkanotra.ttassignment.controller;

import com.daffodil.ritikkanotra.ttassignment.entity.Department;
import com.daffodil.ritikkanotra.ttassignment.entity.Employee;
import com.daffodil.ritikkanotra.ttassignment.error.DepartmentNotFoundException;
import com.daffodil.ritikkanotra.ttassignment.service.DepartmentService;
import com.daffodil.ritikkanotra.ttassignment.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepartmentController departmentController;

    @MockBean
    private DepartmentService departmentService;

    private Department theDepartment;

    @BeforeEach
    void setUp() {

        theDepartment =
                Department.builder()
                        .departmentId(1L)
                        .departmentCode("CSE")
                        .departmentName("Computer Science and Engineering")
                        .build();

    }

    @Test
    void saveDepartment() throws Exception {

        Department department =
                Department.builder()
                        .departmentId(1L)
                        .departmentCode("CSE")
                        .departmentName("Computer Science and Engineering")
                        .build();


        Mockito.when(departmentService.saveDepartment(department)).thenReturn(theDepartment);

        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\n" +
                                "    \"departmentCode\":\"ME\",\n" +
                                "    \"departmentName\":\"M Engineering\"\n" +
                                "}"
                )).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void fetchDepartmentsList() throws Exception {

        Mockito.when(departmentService.fetchDepartmentsList()).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/departments"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void fetchDepartmentByDepartmentCode() throws Exception {
        String departmentCode = "CSE";
        Mockito.when(departmentService.fetchDepartmentByDepartmentCode(departmentCode)).thenReturn(theDepartment);
        mockMvc.perform(MockMvcRequestBuilders.get("/departments/code/" + departmentCode))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void removeDepartmentByDepartmentCode() throws Exception {
        String departmentCode = "CSE";
        mockMvc.perform(MockMvcRequestBuilders.delete("/departments/code/" + departmentCode))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void updateDepartmentByDepartmentCode() throws Exception {
        String departmentCode = "CSE";

       Department updatedDepartment = Department.builder()
                       .departmentCode("CSE")
                               .departmentName("Computer Service")
                                       .build();

        Mockito.when(departmentService.updateDepartmentByDepartmentCode(departmentCode, updatedDepartment))
                .thenReturn(updatedDepartment);

        mockMvc.perform(MockMvcRequestBuilders.put("/departments/code/" + departmentCode)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"departmentCode\": \"Computer Service\"\n" +
                        "}")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}