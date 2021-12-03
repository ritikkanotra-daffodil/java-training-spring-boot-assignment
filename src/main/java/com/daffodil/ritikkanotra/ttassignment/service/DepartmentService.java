package com.daffodil.ritikkanotra.ttassignment.service;

import com.daffodil.ritikkanotra.ttassignment.entity.Department;
import com.daffodil.ritikkanotra.ttassignment.entity.Employee;
import com.daffodil.ritikkanotra.ttassignment.error.DepartmentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DepartmentService {
    public Department saveDepartment(Department department);

    public List<Department> fetchDepartmentsList();

    public Department fetchDepartmentByDepartmentCode(String departmentCode) throws DepartmentNotFoundException;

    public void removeDepartmentByEmployeeCode(String departmentCode) throws DepartmentNotFoundException;

    public Department updateDepartmentByDepartmentCode(String departmentCode, Department department) throws DepartmentNotFoundException;

}
