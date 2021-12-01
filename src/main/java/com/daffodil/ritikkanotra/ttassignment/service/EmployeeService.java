package com.daffodil.ritikkanotra.ttassignment.service;

import com.daffodil.ritikkanotra.ttassignment.entity.Department;
import com.daffodil.ritikkanotra.ttassignment.entity.Employee;
import com.daffodil.ritikkanotra.ttassignment.error.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {

    public Employee saveEmployee(Employee employee);

    public List<Employee> fetchEmployeesList();

    public Employee fetchEmployeeByEmployeeCode(String employeeCode) throws EmployeeNotFoundException;

    public void removeEmployeeByEmployeeCode(String employeeCode) throws EmployeeNotFoundException;
}
