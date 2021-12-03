package com.daffodil.ritikkanotra.ttassignment.repository;

import com.daffodil.ritikkanotra.ttassignment.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    public Optional<Department> findByDepartmentCode(String departmentCode);

    public void deleteByDepartmentCode(String departmentCode);

}
