package com.daffodil.ritikkanotra.ttassignment.repository;

import com.daffodil.ritikkanotra.ttassignment.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public Employee findByEmployeeCode(String employeeCode);

}