package com.daffodil.ritikkanotra.ttassignment.repository;

import com.daffodil.ritikkanotra.ttassignment.entity.Department;
import com.daffodil.ritikkanotra.ttassignment.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    private Department theDepartment;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        theDepartment =
                Department.builder()
                        .departmentCode("CSE")
                        .departmentName("Computer Science and Engineering")
                        .build();
        entityManager.persist(theDepartment);

    }

    @Test
    @DisplayName("Return department if found in database")
    public void whenFindByDepartmentCode_thenReturnDepartment() {
        String departmentCode = "CSE";
        Department department = departmentRepository.findByDepartmentCode(departmentCode).get();
        assertEquals(department, theDepartment);
    }

    @Test
    @DisplayName("Save/Update department in database")
    public void whenSave_thenSaveAndReturnSavedDepartment() {
        Department updatedDepartment = Department.builder()
                .departmentCode("CSE")
                .departmentName("Computer Science")
                .build();

        assertEquals(departmentRepository.save(updatedDepartment), updatedDepartment);
    }

    @Test
    @DisplayName("Fetch all departments from database")
    public void findAll_thenReturnDepartmentList() {
        List<Department> found = departmentRepository.findAll();
        assertEquals(found.get(0), theDepartment);
    }
}