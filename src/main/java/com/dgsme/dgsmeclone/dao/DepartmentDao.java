package com.dgsme.dgsmeclone.dao;

import com.dgsme.dgsmeclone.dto.DepartmentDto;
import com.dgsme.dgsmeclone.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DepartmentDao {
    private final DepartmentRepository repository;

    @Autowired
    public DepartmentDao(DepartmentRepository repository) {
        this.repository = repository;
    }

    public DepartmentDto saveDepartment(DepartmentDto department) {
        validateDepartment(department);
        return repository.save(department);
    }

    public Optional<DepartmentDto> getDepartmentById(Long id) {
        return repository.findById(id);
    }

    public Optional<DepartmentDto> getDepartmentByEmpId(String empId) {
        return repository.findByEmployeeId(empId);
    }

    public List<DepartmentDto> getDepartmentsByDeptName(String deptName) {
        return repository.findByEmployeeDepartment(deptName);
    }

    public List<DepartmentDto> getDepartmentsByManagerId(Long managerId) {
        return repository.findByManagerId(managerId);
    }

    public List<DepartmentDto> searchDepartmentsByEmpName(String name) {
        return repository.findByEmpNameContaining(name);
    }

    public Optional<DepartmentDto> getDepartmentByEmpIdAndManagerId(Long empId, Long managerId) {
        return repository.findByEmpIdAndManagerId(empId, managerId);
    }

    public List<DepartmentDto> getAllDepartments() {
        return repository.findAll();
    }

    public void deleteDepartmentByEmployeeId(String id) {
        repository.deleteByEmployeeId(id);
    }

    public void deleteDepartmentById(Long id) {
        Optional<DepartmentDto> department = repository.findById(id);
        if (department.isPresent()) {
            repository.delete(department.get());
        } else {
            throw new RuntimeException("Department not found with id: " + id);
        }
    }



    private void validateDepartment(DepartmentDto department) {
        if (department.getEmployeeId() == null) {
            throw new IllegalArgumentException("Employee ID cannot be null");
        }
        if (department.getEmployeeName() == null || department.getEmployeeName().trim().isEmpty()) {
            throw new IllegalArgumentException("Employee name cannot be empty");
        }
        if (department.getEmployeeDepartment() == null || department.getEmployeeDepartment().trim().isEmpty()) {
            throw new IllegalArgumentException("Department name cannot be empty");
        }
        if (department.getManagerId() == null) {
            throw new IllegalArgumentException("Manager ID cannot be null");
        }
    }
} 