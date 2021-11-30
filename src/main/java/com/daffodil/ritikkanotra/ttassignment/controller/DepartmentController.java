package com.daffodil.ritikkanotra.ttassignment.controller;

import com.daffodil.ritikkanotra.ttassignment.entity.Department;
import com.daffodil.ritikkanotra.ttassignment.entity.Employee;
import com.daffodil.ritikkanotra.ttassignment.service.DepartmentService;
import com.daffodil.ritikkanotra.ttassignment.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/departments")
    public Department saveDepartment(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/departments")
    public List<Department> fetchDepartmentsList() {
        return departmentService.fetchDepartmentsList();
    }

    @GetMapping("/departments/code/{departmentCode}")
    public Department fetchDepartmentByDepartmentCode(@PathVariable("departmentCode") String departmentCode) {

        return departmentService.fetchDepartmentByDepartmentCode(departmentCode);

    }
}
