package com.dgsme.dgsmeclone.service;

import com.dgsme.dgsmeclone.dao.DepartmentDao;
import com.dgsme.dgsmeclone.dto.DepartmentDto;
import com.dgsme.dgsmeclone.util.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentDao departmentDao;

    @Autowired
    public DepartmentService(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public ResponseStructure<DepartmentDto> addDepartment(DepartmentDto department) {
        DepartmentDto savedDepartment = departmentDao.saveDepartment(department);
        ResponseStructure<DepartmentDto> response = new ResponseStructure<>();
        response.setStatus(201);
        response.setMessage("Department added successfully.");
        response.setData(savedDepartment);
        return response;
    }

    public ResponseStructure<DepartmentDto> getDepartmentById(Long id) {
        Optional<DepartmentDto> department = departmentDao.getDepartmentById(id);
        ResponseStructure<DepartmentDto> response = new ResponseStructure<>();
        if (department.isPresent()) {
            response.setStatus(200);
            response.setMessage("Department found.");
            response.setData(department.get());
        } else {
            response.setStatus(404);
            response.setMessage("Department not found.");
            response.setData(null);
        }
        return response;
    }

    public ResponseStructure<DepartmentDto> getDepartmentByEmpId(String empId) {
        Optional<DepartmentDto> department = departmentDao.getDepartmentByEmpId(empId);
        ResponseStructure<DepartmentDto> response = new ResponseStructure<>();
        if (department.isPresent()) {
            response.setStatus(200);
            response.setMessage("Department found.");
            response.setData(department.get());
        } else {
            response.setStatus(404);
            response.setMessage("Department not found.");
            response.setData(null);
        }
        return response;
    }

    public ResponseStructure<List<DepartmentDto>> getAllDepartments() {
        List<DepartmentDto> departments = departmentDao.getAllDepartments();
        ResponseStructure<List<DepartmentDto>> response = new ResponseStructure<>();
        response.setStatus(200);
        response.setMessage("Departments fetched successfully.");
        response.setData(departments);
        return response;
    }

    public ResponseStructure<List<DepartmentDto>> getDepartmentsByDeptName(String deptName) {
        List<DepartmentDto> departments = departmentDao.getDepartmentsByDeptName(deptName);
        ResponseStructure<List<DepartmentDto>> response = new ResponseStructure<>();
        response.setStatus(200);
        response.setMessage("Departments fetched successfully.");
        response.setData(departments);
        return response;
    }

    public ResponseStructure<List<DepartmentDto>> getDepartmentsByManagerId(Long managerId) {
        List<DepartmentDto> departments = departmentDao.getDepartmentsByManagerId(managerId);
        ResponseStructure<List<DepartmentDto>> response = new ResponseStructure<>();
        response.setStatus(200);
        response.setMessage("Departments fetched successfully.");
        response.setData(departments);
        return response;
    }

    public ResponseStructure<List<DepartmentDto>> searchDepartmentsByEmpName(String name) {
        List<DepartmentDto> departments = departmentDao.searchDepartmentsByEmpName(name);
        ResponseStructure<List<DepartmentDto>> response = new ResponseStructure<>();
        response.setStatus(200);
        response.setMessage("Departments fetched successfully.");
        response.setData(departments);
        return response;
    }

    public ResponseStructure<DepartmentDto> getDepartmentByEmpIdAndManagerId(Long empId, Long managerId) {
        Optional<DepartmentDto> department = departmentDao.getDepartmentByEmpIdAndManagerId(empId, managerId);
        ResponseStructure<DepartmentDto> response = new ResponseStructure<>();
        if (department.isPresent()) {
            response.setStatus(200);
            response.setMessage("Department found.");
            response.setData(department.get());
        } else {
            response.setStatus(404);
            response.setMessage("Department not found.");
            response.setData(null);
        }
        return response;
    }

    public ResponseStructure<DepartmentDto> updateDepartment(Long id, DepartmentDto updatedDepartment) {
        Optional<DepartmentDto> department = departmentDao.getDepartmentById(id);
        ResponseStructure<DepartmentDto> response = new ResponseStructure<>();
        if (department.isPresent()) {
            department.get().setEmployeeId(updatedDepartment.getEmployeeId());
            department.get().setEmployeeName(updatedDepartment.getEmployeeName());
            department.get().setEmployeeDepartment(updatedDepartment.getEmployeeDepartment());
            department.get().setManagerId(updatedDepartment.getManagerId());
            DepartmentDto updated = departmentDao.saveDepartment(department.get());
            response.setStatus(200);
            response.setMessage("Department updated successfully.");
            response.setData(updated);
        } else {
            response.setStatus(404);
            response.setMessage("Department not found.");
            response.setData(null);
        }
        return response;
    }

    public ResponseStructure<Void> deleteDepartment(Long id) {
        Optional<DepartmentDto> department = departmentDao.getDepartmentById(id);
        ResponseStructure<Void> response = new ResponseStructure<>();
        if (department.isPresent()) {
            departmentDao.deleteDepartmentById(id);
            response.setStatus(204);
            response.setMessage("Department deleted successfully.");
            response.setData(null);
        } else {
            response.setStatus(404);
            response.setMessage("Department not found.");
            response.setData(null);
        }
        return response;
    }
}
