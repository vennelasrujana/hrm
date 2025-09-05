package com.dgsme.dgsmeclone.controller;

import com.dgsme.dgsmeclone.dto.DepartmentDto;
import com.dgsme.dgsmeclone.service.DepartmentService;
import com.dgsme.dgsmeclone.util.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/departments")

public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<ResponseStructure<DepartmentDto>> addDepartment(@RequestBody DepartmentDto department) {
        ResponseStructure<DepartmentDto> response = departmentService.addDepartment(department);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<DepartmentDto>> getDepartmentById(@PathVariable Long id) {
        ResponseStructure<DepartmentDto> response = departmentService.getDepartmentById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/employee/{empId}")
    public ResponseEntity<ResponseStructure<DepartmentDto>> getDepartmentByEmpId(@PathVariable String empId) {
        ResponseStructure<DepartmentDto> response = departmentService.getDepartmentByEmpId(empId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<DepartmentDto>>> getAllDepartments() {
        ResponseStructure<List<DepartmentDto>> response = departmentService.getAllDepartments();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/dept/{deptName}")
    public ResponseEntity<ResponseStructure<List<DepartmentDto>>> getDepartmentsByDeptName(@PathVariable String deptName) {
        ResponseStructure<List<DepartmentDto>> response = departmentService.getDepartmentsByDeptName(deptName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/manager/{managerId}")
    public ResponseEntity<ResponseStructure<List<DepartmentDto>>> getDepartmentsByManagerId(@PathVariable Long managerId) {
        ResponseStructure<List<DepartmentDto>> response = departmentService.getDepartmentsByManagerId(managerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseStructure<List<DepartmentDto>>> searchDepartmentsByEmpName(@RequestParam String name) {
        ResponseStructure<List<DepartmentDto>> response = departmentService.searchDepartmentsByEmpName(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/employee-manager")
    public ResponseEntity<ResponseStructure<DepartmentDto>> getDepartmentByEmpIdAndManagerId(
            @RequestParam Long empId,
            @RequestParam Long managerId) {
        ResponseStructure<DepartmentDto> response = departmentService.getDepartmentByEmpIdAndManagerId(empId, managerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<DepartmentDto>> updateDepartment(
            @PathVariable Long id,
            @RequestBody DepartmentDto updatedDepartment) {
        ResponseStructure<DepartmentDto> response = departmentService.updateDepartment(id, updatedDepartment);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<Void>> deleteDepartment(@PathVariable Long id) {
        ResponseStructure<Void> response = departmentService.deleteDepartment(id);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
