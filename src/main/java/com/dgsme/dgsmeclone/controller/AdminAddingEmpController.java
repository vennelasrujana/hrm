package com.dgsme.dgsmeclone.controller;

import com.dgsme.dgsmeclone.dto.AdminAddingEmpDto;
import com.dgsme.dgsmeclone.dto.EmployeeStatsDto;
import com.dgsme.dgsmeclone.dto.EmployeeWithPunchDataDto;
import com.dgsme.dgsmeclone.service.AdminAddingEmpService;
import com.dgsme.dgsmeclone.util.ResponseStructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/admin/employees")

public class AdminAddingEmpController {

    private final AdminAddingEmpService adminAddingEmpService;

    @Autowired
    public AdminAddingEmpController(AdminAddingEmpService adminAddingEmpService) {
        this.adminAddingEmpService = adminAddingEmpService;
    }

    @PostMapping
    public ResponseEntity<ResponseStructure<AdminAddingEmpDto>> addEmployee(@RequestBody AdminAddingEmpDto employee) {
        return new ResponseEntity<>(adminAddingEmpService.addEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping("/getEmpById/{id}")
    public ResponseEntity<ResponseStructure<AdminAddingEmpDto>> getEmployeeById(@PathVariable String id) {
    	
        ResponseStructure<AdminAddingEmpDto> response = adminAddingEmpService.getEmployeeById(id);
        HttpStatus status = response.getData() != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseStructure<AdminAddingEmpDto>> getEmployeeByEmail(@PathVariable String email) {
        ResponseStructure<AdminAddingEmpDto> response = adminAddingEmpService.getEmployeeByEmail(email);
        HttpStatus status = response.getData() != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<AdminAddingEmpDto>>> getAllEmployees() {
        return new ResponseEntity<>(adminAddingEmpService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<ResponseStructure<List<AdminAddingEmpDto>>> getEmployeesByDepartment(@PathVariable String department) {
        return new ResponseEntity<>(adminAddingEmpService.getEmployeesByDepartment(department), HttpStatus.OK);
    }

    @GetMapping("/position/{position}")
    public ResponseEntity<ResponseStructure<List<AdminAddingEmpDto>>> getEmployeesByPosition(@PathVariable String position) {
        return new ResponseEntity<>(adminAddingEmpService.getEmployeesByPosition(position), HttpStatus.OK);
    } 

    @GetMapping("/search")
    public ResponseEntity<ResponseStructure<List<AdminAddingEmpDto>>> searchEmployeesByName(@RequestParam String name) {
        return new ResponseEntity<>(adminAddingEmpService.searchEmployeesByName(name), HttpStatus.OK);
    }

    @GetMapping("/join-date-range")
    public ResponseEntity<ResponseStructure<List<AdminAddingEmpDto>>> getEmployeesByJoinDateRange(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return new ResponseEntity<>(adminAddingEmpService.getEmployeesByJoinDateRange(startDate, endDate), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<AdminAddingEmpDto>> updateEmployee(
            @PathVariable String id,
            @RequestBody AdminAddingEmpDto updatedEmployee) {
        try {
            ResponseStructure<AdminAddingEmpDto> response = adminAddingEmpService.updateEmployee(id, updatedEmployee);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            ResponseStructure<AdminAddingEmpDto> error = new ResponseStructure<>();
            error.setStatus(HttpStatus.NOT_FOUND.value());
            error.setMessage(e.getMessage());
            error.setData(null);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteEmployee(@PathVariable String id) {
        try {
            return new ResponseEntity<>(adminAddingEmpService.deleteEmployee(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            ResponseStructure<String> error = new ResponseStructure<>();
            error.setStatus(HttpStatus.NOT_FOUND.value());
            error.setMessage(e.getMessage());
            error.setData(null);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employee/{employeeId}/details")
    public ResponseEntity<ResponseStructure<EmployeeWithPunchDataDto>> getEmployeeDetails(@PathVariable int employeeId) {
        return new ResponseEntity<>(adminAddingEmpService.getEmployeeDetailsWithPunchData(employeeId), HttpStatus.OK);
    }

    @GetMapping("/stats")
    public ResponseEntity<ResponseStructure<EmployeeStatsDto>> getEmployeeStats() {
        return new ResponseEntity<>(adminAddingEmpService.getEmployeeStats(), HttpStatus.OK);
    }
}
