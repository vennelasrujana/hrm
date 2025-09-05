package com.dgsme.dgsmeclone.service;

import com.dgsme.dgsmeclone.dao.AdminAddingEmpDao;
import com.dgsme.dgsmeclone.dao.DepartmentDao;
import com.dgsme.dgsmeclone.dao.EmployeeLoginCredentialsDao;
import com.dgsme.dgsmeclone.dao.LeaveRequestDao;
import com.dgsme.dgsmeclone.dao.PunchInDao;
import com.dgsme.dgsmeclone.dao.PunchOutDao;
import com.dgsme.dgsmeclone.dto.AdminAddingEmpDto;
import com.dgsme.dgsmeclone.dto.EmployeeStatsDto;
import com.dgsme.dgsmeclone.dto.EmployeeWithPunchDataDto;
import com.dgsme.dgsmeclone.util.ResponseStructure;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdminAddingEmpService {

    private final AdminAddingEmpDao adminAddingEmpDao;

    @Autowired
    private EmployeeLoginCredentialsDao employeeLoginCredentialsDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private LeaveRequestDao leaveRequestDao;

    @Autowired
    private PunchInDao punchInDao;

    @Autowired
    private PunchOutDao punchOutDao;

    @Autowired
    public AdminAddingEmpService(AdminAddingEmpDao adminAddingEmpDao) {
        this.adminAddingEmpDao = adminAddingEmpDao;
    }

    public ResponseStructure<AdminAddingEmpDto> addEmployee(AdminAddingEmpDto employee) {
        if (employee.getJoinDate() == null) {
            employee.setJoinDate(LocalDateTime.now());
        }

        Optional<AdminAddingEmpDto> existingEmployee = adminAddingEmpDao.getEmployeeByEmployeeId(employee.getEmployeeId());
        if (existingEmployee.isPresent()) {
            throw new RuntimeException("Employee with ID " + employee.getEmployeeId() + " already exists.");
        }

        existingEmployee = adminAddingEmpDao.getEmployeeByEmail(employee.getEmployeeEmail());
        if (existingEmployee.isPresent()) {
            throw new RuntimeException("Employee with email " + employee.getEmployeeEmail() + " already exists.");
        }

        AdminAddingEmpDto savedEmployee = adminAddingEmpDao.saveEmployee(employee);

        ResponseStructure<AdminAddingEmpDto> responseStructure = new ResponseStructure<>();
        responseStructure.setStatus(201);
        responseStructure.setMessage("Employee added successfully");
        responseStructure.setData(savedEmployee);

        return responseStructure;
    }

    public ResponseStructure<AdminAddingEmpDto> getEmployeeById(String id) {
        Optional<AdminAddingEmpDto> employee = adminAddingEmpDao.getEmployeeByEmployeeId(id);
        ResponseStructure<AdminAddingEmpDto> response = new ResponseStructure<>();
        if (employee.isPresent()) {
            response.setStatus(200);
            response.setMessage("Employee found");
            response.setData(employee.get());
        } else {
            response.setStatus(404);
            response.setMessage("Employee not found with id: " + id);
            response.setData(null);
        }
        return response;
    }

    public ResponseStructure<AdminAddingEmpDto> getEmployeeByEmail(String email) {
        Optional<AdminAddingEmpDto> employee = adminAddingEmpDao.getEmployeeByEmail(email);
        ResponseStructure<AdminAddingEmpDto> response = new ResponseStructure<>();
        if (employee.isPresent()) {
            response.setStatus(200);
            response.setMessage("Employee found");
            response.setData(employee.get());
        } else {
            response.setStatus(404);
            response.setMessage("Employee not found with email: " + email);
            response.setData(null);
        }
        return response;
    }

    public ResponseStructure<List<AdminAddingEmpDto>> getAllEmployees() {
        List<AdminAddingEmpDto> employees = adminAddingEmpDao.getAllEmployees();
        ResponseStructure<List<AdminAddingEmpDto>> response = new ResponseStructure<>();
        response.setStatus(200);
        response.setMessage("All employees fetched successfully");
        response.setData(employees);
        return response;
    }

    public ResponseStructure<List<AdminAddingEmpDto>> getEmployeesByDepartment(String department) {
        List<AdminAddingEmpDto> employees = adminAddingEmpDao.getEmployeesByDepartment(department);
        ResponseStructure<List<AdminAddingEmpDto>> response = new ResponseStructure<>();
        response.setStatus(200);
        response.setMessage("Employees by department fetched successfully");
        response.setData(employees);
        return response;
    }

    public ResponseStructure<List<AdminAddingEmpDto>> getEmployeesByPosition(String position) {
        List<AdminAddingEmpDto> employees = adminAddingEmpDao.getEmployeesByPosition(position);
        ResponseStructure<List<AdminAddingEmpDto>> response = new ResponseStructure<>();
        response.setStatus(200);
        response.setMessage("Employees by position fetched successfully");
        response.setData(employees);
        return response;
    }

    public ResponseStructure<List<AdminAddingEmpDto>> searchEmployeesByName(String name) {
        List<AdminAddingEmpDto> employees = adminAddingEmpDao.searchEmployeesByName(name);
        ResponseStructure<List<AdminAddingEmpDto>> response = new ResponseStructure<>();
        response.setStatus(200);
        response.setMessage("Employees found by name search");
        response.setData(employees);
        return response;
    }

    public ResponseStructure<List<AdminAddingEmpDto>> getEmployeesByJoinDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<AdminAddingEmpDto> employees = adminAddingEmpDao.getEmployeesByJoinDateRange(startDate, endDate);
        ResponseStructure<List<AdminAddingEmpDto>> response = new ResponseStructure<>();
        response.setStatus(200);
        response.setMessage("Employees fetched by join date range");
        response.setData(employees);
        return response;
    }

    public ResponseStructure<AdminAddingEmpDto> updateEmployee(String id, AdminAddingEmpDto updatedEmployee) {
        return adminAddingEmpDao.getEmployeeByEmployeeId(id)
                .map(existingEmployee -> {
                    existingEmployee.setEmployeeName(updatedEmployee.getEmployeeName());
                    existingEmployee.setEmployeeEmail(updatedEmployee.getEmployeeEmail());
                    existingEmployee.setEmployeePosition(updatedEmployee.getEmployeePosition());
                    existingEmployee.setEmployeeDepartment(updatedEmployee.getEmployeeDepartment());
                    existingEmployee.setEmployeePhone(updatedEmployee.getEmployeePhone());
                    existingEmployee.setEmployeeAddress(updatedEmployee.getEmployeeAddress());
                    existingEmployee.setEmployeeGender(updatedEmployee.getEmployeeGender());
                    existingEmployee.setEmployeeMaritalStatus(updatedEmployee.getEmployeeMaritalStatus());
                    existingEmployee.setEmployeeDOB(updatedEmployee.getEmployeeDOB());
                    existingEmployee.setJoinDate(updatedEmployee.getJoinDate() != null ? updatedEmployee.getJoinDate() : existingEmployee.getJoinDate());
                    existingEmployee.setShiftType(updatedEmployee.getShiftType());
                    existingEmployee.setEmployeeShift(updatedEmployee.getEmployeeShift());
                    AdminAddingEmpDto saved = adminAddingEmpDao.saveEmployee(existingEmployee);
                    ResponseStructure<AdminAddingEmpDto> response = new ResponseStructure<>();
                    response.setStatus(200);
                    response.setMessage("Employee updated successfully");
                    response.setData(saved);
                    return response;
                })
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    @Transactional
    public ResponseStructure<String> deleteEmployee(String id) {
        adminAddingEmpDao.deleteEmployee(id);
        employeeLoginCredentialsDao.deleteEmployeeCredentials(id);
        departmentDao.deleteDepartmentByEmployeeId(id);
        punchInDao.deletePunchIn(id);
        punchOutDao.deletePunchOut(id);
        leaveRequestDao.deleteLeaveRequest(id);
        ResponseStructure<String> response = new ResponseStructure<>();
        response.setStatus(200);
        response.setMessage("Employee deleted successfully");
        response.setData("Deleted employee with ID: " + id);
        return response;
    }

    public ResponseStructure<EmployeeWithPunchDataDto> getEmployeeDetailsWithPunchData(int employeeId) {
        EmployeeWithPunchDataDto details = adminAddingEmpDao.getEmployeeDetailsWithPunchData(employeeId);
        ResponseStructure<EmployeeWithPunchDataDto> response = new ResponseStructure<>();
        response.setStatus(200);
        response.setMessage("Employee details with punch data fetched successfully");
        response.setData(details);
        return response;
    }

    public ResponseStructure<EmployeeStatsDto> getEmployeeStats() {
        EmployeeStatsDto stats = adminAddingEmpDao.getEmployeeStats();
        ResponseStructure<EmployeeStatsDto> response = new ResponseStructure<>();
        response.setStatus(200);
        response.setMessage("Employee statistics fetched successfully");
        response.setData(stats);
        return response;
    }
}
