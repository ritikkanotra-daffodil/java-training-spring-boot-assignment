package com.daffodil.ritikkanotra.ttassignment.repository;

import com.daffodil.ritikkanotra.ttassignment.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee theEmployee;

    @Autowired
    private TestEntityManager entityManager;

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
        entityManager.persist(theEmployee);

    }

    @Test
    @DisplayName("Return employee if found in database")
    public void whenFindByEmployeeCode_thenReturnEmployee() {
        String employeeCode = "APS-101";
        Employee employee = employeeRepository.findByEmployeeCode(employeeCode).get();
        assertEquals(employee, theEmployee);
    }

    @Test
    @DisplayName("Save/Update employee in database")
    public void whenSave_thenSaveAndReturnSavedEmployee() {
        Employee updatedEmployee = Employee.builder()
                .employeeCode("APS-101")
                .employeeName("Ritik kanotra")
                .employeeDepartment("CSE")
                .employeeEmail("ritik.kanotra@unthinkable.co")
                .employeeDesignation("Software Development Engineer")
                .build();

        assertEquals(employeeRepository.save(updatedEmployee), updatedEmployee);
    }

    @Test
    @DisplayName("Fetch all employees from database")
    public void findAll_thenReturnEmployeeList() {
        List<Employee> found = employeeRepository.findAll();
        assertEquals(found.get(0), theEmployee);
    }





}