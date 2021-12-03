package com.daffodil.ritikkanotra.ttassignment.service;

import com.daffodil.ritikkanotra.ttassignment.entity.Employee;
import com.daffodil.ritikkanotra.ttassignment.error.EmployeeNotFoundException;
import com.daffodil.ritikkanotra.ttassignment.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> fetchEmployeesList() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee fetchEmployeeByEmployeeCode(String employeeCode) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepository.findByEmployeeCode(employeeCode);
        if (!employee.isPresent()) {
            throw new EmployeeNotFoundException("Employee not found!");
        }
        return employee.get();
    }

    @Override
    public void removeEmployeeByEmployeeCode(String employeeCode) throws EmployeeNotFoundException {

        Optional<Employee> employee = employeeRepository.findByEmployeeCode(employeeCode);

        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee not found");
        }
        employeeRepository.deleteByEmployeeCode(employeeCode);
    }

    @Override
    public Employee updateEmployeeByEmployeeCode(String employeeCode, Employee employee) throws EmployeeNotFoundException {
        Optional<Employee> found = employeeRepository.findByEmployeeCode(employeeCode);
        if (!found.isPresent()) {
            throw new EmployeeNotFoundException("Employee not found.");
        }
        Employee dbEmployee = found.get();
        if (employee.getEmployeeCode() != null && !employee.getEmployeeCode().equals("")) {
            dbEmployee.setEmployeeCode(employee.getEmployeeCode());
        }
        if (employee.getEmployeeName() != null && !employee.getEmployeeName().equals("")) {
            dbEmployee.setEmployeeName(employee.getEmployeeName());
        }
        if (employee.getEmployeeEmail() != null && !employee.getEmployeeEmail().equals("")) {
            dbEmployee.setEmployeeEmail(employee.getEmployeeEmail());
        }
        if (employee.getEmployeeDepartment() != null && !employee.getEmployeeDepartment().equals("")) {
            dbEmployee.setEmployeeDepartment(employee.getEmployeeDepartment());
        }
        if (employee.getEmployeeDesignation() != null && !employee.getEmployeeDesignation().equals("")) {
            dbEmployee.setEmployeeDesignation(employee.getEmployeeDesignation());
        }
        return employeeRepository.save(dbEmployee);
    }
}
