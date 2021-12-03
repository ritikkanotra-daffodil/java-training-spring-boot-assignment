package com.daffodil.ritikkanotra.ttassignment.service;

import com.daffodil.ritikkanotra.ttassignment.entity.Department;
import com.daffodil.ritikkanotra.ttassignment.entity.Employee;
import com.daffodil.ritikkanotra.ttassignment.error.DepartmentNotFoundException;
import com.daffodil.ritikkanotra.ttassignment.error.EmployeeNotFoundException;
import com.daffodil.ritikkanotra.ttassignment.repository.DepartmentRepository;
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
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    private Department theDepartment;

    @BeforeEach
    void setUp() {
        theDepartment =
                Department.builder()
                        .departmentCode("CSE")
                        .departmentName("Computer Sience and Engineering")
                        .build();


        Mockito.when(departmentRepository.findByDepartmentCode("CSE"))
                .thenReturn(Optional.ofNullable(theDepartment));

        Department updatedDepartment =
                Department.builder()
                        .departmentCode("CSE")
                        .departmentName("Computer Science")
                        .build();

        Mockito.when(departmentRepository.save(updatedDepartment))
                .thenReturn(updatedDepartment);

        Mockito.when(departmentRepository.save(theDepartment))
                .thenReturn(theDepartment);

        List<Department> departmentList = new ArrayList<>();
        departmentList.add(theDepartment);

        Mockito.when(departmentRepository.findAll())
                .thenReturn(departmentList);
    }

    @Test
    @DisplayName("Get department on VALID department code")
    public void whenValidDepartmentCode_thenDepartmentShouldFound() throws DepartmentNotFoundException {
        String departmentCode = "CSE";
        Department found = departmentService.fetchDepartmentByDepartmentCode(departmentCode);
        assertEquals(departmentCode, found.getDepartmentCode());
    }


    @Test
    @DisplayName("Update department on VALID department code")
    public void whenDepartmentCodeFound_thenDepartmentShouldBeUpdated() throws DepartmentNotFoundException {
        String departmentCode = "CSE";
        Department department =
                Department.builder()
                        .departmentCode("CSE")
                        .departmentName("Computer Science and Engineering")
                        .build();
        Department updatedDepartment = departmentService.updateDepartmentByDepartmentCode(departmentCode, department);
        assertEquals(department.getDepartmentName(), updatedDepartment.getDepartmentName());
    }

    @Test
    @DisplayName("Check if new department is created as expected")
    public void checkIfDepartmentIsCreatedCorrectly() {
        Department createdDepartment = departmentService.saveDepartment(theDepartment);
        assertEquals(theDepartment, createdDepartment);
    }

    @Test
    @DisplayName("Check if department List is returned as expected")
    public void checkIfDepartmentListIsReturnedCorrectly() {
        List<Department> expectedDepartmentList = new ArrayList<>();
        expectedDepartmentList.add(theDepartment);
        List<Department> actual = departmentService.fetchDepartmentsList();
        assertEquals(expectedDepartmentList, actual);
    }

}