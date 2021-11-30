package com.daffodil.ritikkanotra.ttassignment.repository;

import com.daffodil.ritikkanotra.ttassignment.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
