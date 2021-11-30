package com.daffodil.ritikkanotra.ttassignment.service;

import com.daffodil.ritikkanotra.ttassignment.entity.Department;
import com.daffodil.ritikkanotra.ttassignment.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public Employee saveEmployee(Employee employee);

    public List<Employee> fetchEmployeesList();

}
