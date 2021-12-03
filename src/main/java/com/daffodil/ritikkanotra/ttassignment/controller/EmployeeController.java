package com.daffodil.ritikkanotra.ttassignment.controller;

import com.daffodil.ritikkanotra.ttassignment.entity.Employee;
import com.daffodil.ritikkanotra.ttassignment.error.EmployeeNotFoundException;
import com.daffodil.ritikkanotra.ttassignment.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/employees")
    public List<Employee> fetchEmployeesList() {
        return employeeService.fetchEmployeesList();
    }

    @GetMapping("/employees/code/{employeeCode}")
    public Employee fetchEmployeeByEmployeeCode(@PathVariable("employeeCode") String employeeCode) throws EmployeeNotFoundException {
        return employeeService.fetchEmployeeByEmployeeCode(employeeCode);
    }

    @DeleteMapping("/employees/code/{employeeCode}")
    @Transactional
    public String removeEmployeeByEmployeeCode(@PathVariable("employeeCode") String employeeCode) throws EmployeeNotFoundException {
        employeeService.removeEmployeeByEmployeeCode(employeeCode);
        return "Removed " + employeeCode + " successfully.";
    }

    @PutMapping("/employees/code/{employeeCode}")
    public Employee updateEmployeeByEmployeeCode(
            @PathVariable("employeeCode") String employeeCode,
            @RequestBody Employee employee) throws EmployeeNotFoundException {

        return employeeService.updateEmployeeByEmployeeCode(employeeCode, employee);

    }



}
