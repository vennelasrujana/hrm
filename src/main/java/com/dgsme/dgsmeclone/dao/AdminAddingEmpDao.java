package com.dgsme.dgsmeclone.dao;

import com.dgsme.dgsmeclone.dto.AdminAddingEmpDto;
import com.dgsme.dgsmeclone.dto.EmployeeStatsDto;
import com.dgsme.dgsmeclone.dto.EmployeeWithPunchDataDto;
import com.dgsme.dgsmeclone.dto.PunchInDto;
import com.dgsme.dgsmeclone.dto.PunchOutDto;
import com.dgsme.dgsmeclone.repository.AdminAddingEmpRepository;
import com.dgsme.dgsmeclone.repository.PunchInRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Component
public class AdminAddingEmpDao {

	 private final AdminAddingEmpRepository repository;
	 @Autowired
	 private PunchInRepository punchInRepository;   

	    @Autowired
	    public AdminAddingEmpDao(AdminAddingEmpRepository adminAddingEmpRepository, PunchInRepository punchInRepository) {
	        this.repository = adminAddingEmpRepository;
	      
	    }

  

    public AdminAddingEmpDto saveEmployee(AdminAddingEmpDto employee) {
        validateEmployee(employee);
        return repository.save(employee);
    }

    public Optional<AdminAddingEmpDto> getEmployeeByEmployeeId(String employeeId) {
        return repository.findByEmployeeId(employeeId);
    }

    public Optional<AdminAddingEmpDto> getEmployeeByEmail(String email) {
        return repository.findByEmployeeEmail(email);
    }

    public List<AdminAddingEmpDto> getAllEmployees() {
        return repository.findAll();
    }

    public List<AdminAddingEmpDto> getEmployeesByDepartment(String department) {
        return repository.findByEmployeeDepartment(department);
    }

    public List<AdminAddingEmpDto> getEmployeesByPosition(String position) {
        return repository.findByEmployeePosition(position);
    }

    public List<AdminAddingEmpDto> searchEmployeesByName(String name) {
        return repository.findByEmployeeNameContaining(name);
    }

    public List<AdminAddingEmpDto> getEmployeesByJoinDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findByJoinDateBetween(startDate, endDate);
    }

    public void deleteEmployee(String id) {
        Optional<AdminAddingEmpDto> employee = repository.findByEmployeeId(id);
        if (employee.isEmpty()) {
            throw new RuntimeException("Employee not found with id: " + id);
        }
        repository.deleteEmployeeByEmployeeId(id);
    }

    private void validateEmployee(AdminAddingEmpDto employee) {
        if (employee.getEmployeeName() == null || employee.getEmployeeName().trim().isEmpty()) {
            throw new IllegalArgumentException("Employee name cannot be empty");
        }
        if (employee.getEmployeeEmail() == null || employee.getEmployeeEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Employee email cannot be empty");
        }
        if (employee.getEmployeePosition() == null || employee.getEmployeePosition().trim().isEmpty()) {
            throw new IllegalArgumentException("Employee position cannot be empty");
        }
        if (employee.getEmployeeDepartment() == null || employee.getEmployeeDepartment().trim().isEmpty()) {
            throw new IllegalArgumentException("Employee department cannot be empty");
        }
        if (employee.getEmployeePhone() == null || employee.getEmployeePhone().trim().isEmpty()) {
            throw new IllegalArgumentException("Employee phone cannot be empty");
        }
    }
    
    public EmployeeWithPunchDataDto getEmployeeDetailsWithPunchData(int employeeId) {
        List<Object[]> result = repository.findEmployeeDetailsWithPunchInOut(employeeId);

        if (!result.isEmpty()) {
            Object[] row = result.get(0);
            AdminAddingEmpDto employee = (AdminAddingEmpDto) row[0];
            PunchInDto punchIn = (PunchInDto) row[1];
            PunchOutDto punchOut = (PunchOutDto) row[2];
            
            return new EmployeeWithPunchDataDto(employee, punchIn, punchOut);
        }
        return null; 
    }
    
    public EmployeeStatsDto getEmployeeStats() {
        Long totalEmployees = repository.countTotalEmployees();
        Long todayPunchIns = punchInRepository.countTodayPunchIns();
        
        LocalTime lateTime = LocalTime.of(9, 0);
        Long latePunchIns = punchInRepository.countLatePunchIns(lateTime);

        return new EmployeeStatsDto(totalEmployees, todayPunchIns, latePunchIns);
    }

}
