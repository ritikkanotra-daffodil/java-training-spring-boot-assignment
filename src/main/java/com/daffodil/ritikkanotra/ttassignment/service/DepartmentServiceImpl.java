package com.daffodil.ritikkanotra.ttassignment.service;

import com.daffodil.ritikkanotra.ttassignment.entity.Department;
import com.daffodil.ritikkanotra.ttassignment.error.DepartmentNotFoundException;
import com.daffodil.ritikkanotra.ttassignment.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentsList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentByDepartmentCode(String departmentCode) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findByDepartmentCode(departmentCode);
        if (department.isEmpty()) {
            throw new DepartmentNotFoundException("Department not found.");
        }
        return departmentRepository.findByDepartmentCode(departmentCode).get();
    }

    @Override
    @Transactional
    public void removeDepartmentByEmployeeCode(String departmentCode) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findByDepartmentCode(departmentCode);
        if (department.isEmpty()) {
            throw new DepartmentNotFoundException("Department not found.");
        }
        departmentRepository.deleteByDepartmentCode(departmentCode);

    }

    @Override
    public Department updateDepartmentByDepartmentCode(String departmentCode, Department department) throws DepartmentNotFoundException {
        Optional<Department> found = departmentRepository.findByDepartmentCode(departmentCode);
        if (found.isEmpty()) {
            throw new DepartmentNotFoundException("Department not found.");
        }
        Department dbDepartment = found.get();
        if (department.getDepartmentCode() != null && !department.getDepartmentCode().equals("")) {
            dbDepartment.setDepartmentCode(department.getDepartmentCode());
        }
        if (department.getDepartmentName() != null && !department.getDepartmentName().equals("")) {
            dbDepartment.setDepartmentName(department.getDepartmentName());
        }
        return departmentRepository.save(dbDepartment);
    }
}
