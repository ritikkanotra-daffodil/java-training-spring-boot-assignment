package com.daffodil.ritikkanotra.ttassignment.service;

import com.daffodil.ritikkanotra.ttassignment.entity.Department;
import com.daffodil.ritikkanotra.ttassignment.entity.Employee;
import com.daffodil.ritikkanotra.ttassignment.error.EmployeeNotFoundException;
import com.daffodil.ritikkanotra.ttassignment.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
        if (!employeeRepository.existsByEmployeeCode(employeeCode)) {
            throw new EmployeeNotFoundException("Employee not found");
        }
        employeeRepository.deleteByEmployeeCode(employeeCode);
    }
}
