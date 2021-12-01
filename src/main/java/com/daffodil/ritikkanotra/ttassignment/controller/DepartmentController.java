package com.daffodil.ritikkanotra.ttassignment.controller;

import com.daffodil.ritikkanotra.ttassignment.entity.Department;
import com.daffodil.ritikkanotra.ttassignment.error.DepartmentNotFoundException;
import com.daffodil.ritikkanotra.ttassignment.error.EmployeeNotFoundException;
import com.daffodil.ritikkanotra.ttassignment.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    public Department fetchDepartmentByDepartmentCode(@PathVariable("departmentCode") String departmentCode) throws DepartmentNotFoundException {
        return departmentService.fetchDepartmentByDepartmentCode(departmentCode);
    }

    @DeleteMapping("/departments/code/{departmentCode}")
    @Transactional
    public String removeDepartmentByDepartmentCode(@PathVariable("departmentCode") String departmentCode) throws EmployeeNotFoundException, DepartmentNotFoundException {
        departmentService.removeDepartmentByEmployeeCode(departmentCode);
        return "Removed " + departmentCode + " successfully.";
    }

    @PutMapping("/departments/code/{departmentCode}")
    public Department updateDepartmentByDepartmentCode(
            @PathVariable("departmentCode") String departmentCode,
            @RequestBody Department department) throws EmployeeNotFoundException, DepartmentNotFoundException {

        return departmentService.updateDepartmentByDepartmentCode(departmentCode, department);

    }

}
