package com.daffodil.ritikkanotra.ttassignment.repository;

import com.daffodil.ritikkanotra.ttassignment.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public Optional<Employee> findByEmployeeCode(String employeeCode);

    public void deleteByEmployeeCode(String employeeCode);

    public boolean existsByEmployeeCode(String employeeCode);

}
