package com.dgsme.dgsmeclone.service;

import com.dgsme.dgsmeclone.dao.PunchInDao;
import com.dgsme.dgsmeclone.dto.PunchInDto;
import com.dgsme.dgsmeclone.util.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class PunchInService {

    private final PunchInDao dao;

    @Autowired
    public PunchInService(PunchInDao dao) {
        this.dao = dao;
    }

    public ResponseStructure<PunchInDto> recordPunchIn(PunchInDto punchIn) {
        validatePunchIn(punchIn);

        punchIn.setLoginDate(LocalDate.now());
        punchIn.setLoginTime(LocalTime.now());

        PunchInDto saved = dao.savePunchIn(punchIn);

        ResponseStructure<PunchInDto> response = new ResponseStructure<>();
        response.setStatus(201);
        response.setMessage("Punch-in recorded successfully");
        response.setData(saved);
        return response;
    }

    public ResponseStructure<List<PunchInDto>> getEmployeePunchIns(String employeeId, LocalDate date) {
        List<PunchInDto> punchIns = dao.getPunchInsByEmployeeAndDate(employeeId, date);
        ResponseStructure<List<PunchInDto>> response = new ResponseStructure<>();
        response.setStatus(200);
        response.setMessage("Punch-ins fetched for employee on date");
        response.setData(punchIns);
        return response;
    }

    public ResponseStructure<List<PunchInDto>> getAllPunchInDetailsByEmployeeId(String employeeId) {
        List<PunchInDto> punchIns = dao.getAllPunchInDetailsByEmployeeId(employeeId);
        ResponseStructure<List<PunchInDto>> response = new ResponseStructure<>();
        response.setStatus(200);
        response.setMessage("All punch-in details fetched for employee");
        response.setData(punchIns);
        return response;
    }

    public ResponseStructure<PunchInDto> getLastPunchIn(String employeeId) {
        Optional<PunchInDto> punchIn = dao.getLastPunchIn(employeeId);
        ResponseStructure<PunchInDto> response = new ResponseStructure<>();
        if (punchIn.isPresent()) {
            response.setStatus(200);
            response.setMessage("Last punch-in record found");
            response.setData(punchIn.get());
        } else {
            response.setStatus(404);
            response.setMessage("No punch-in record found for employee ID: " + employeeId);
            response.setData(null);
        }
        return response;
    }

    public ResponseStructure<List<PunchInDto>> getPunchInsByDateRange(String employeeId, LocalDate startDate, LocalDate endDate) {
        validateDateRange(startDate, endDate);
        List<PunchInDto> punchIns = dao.getPunchInsByDateRange(employeeId, startDate, endDate);
        ResponseStructure<List<PunchInDto>> response = new ResponseStructure<>();
        response.setStatus(200);
        response.setMessage("Punch-ins fetched for date range");
        response.setData(punchIns);
        return response;
    }

    public ResponseStructure<Boolean> hasEmployeePunchedInToday(String employeeId) {
        boolean punchedIn = dao.hasPunchedInToday(employeeId);
        ResponseStructure<Boolean> response = new ResponseStructure<>();
        response.setStatus(200);
        response.setMessage("Punch-in status for today retrieved");
        response.setData(punchedIn);
        return response;
    }

    public ResponseStructure<List<PunchInDto>> getLatePunchIns(LocalDate date) {
        List<PunchInDto> punchIns = dao.getLatePunchIns(date);
        ResponseStructure<List<PunchInDto>> response = new ResponseStructure<>();
        response.setStatus(200);
        response.setMessage("Late punch-ins fetched for date");
        response.setData(punchIns);
        return response;
    }

    public ResponseStructure<List<PunchInDto>> getEarlyPunchIns(LocalDate date) {
        List<PunchInDto> punchIns = dao.getEarlyPunchIns(date);
        ResponseStructure<List<PunchInDto>> response = new ResponseStructure<>();
        response.setStatus(200);
        response.setMessage("Early punch-ins fetched for date");
        response.setData(punchIns);
        return response;
    }

    private void validatePunchIn(PunchInDto punchIn) {
        if (punchIn.getEmployeeId() == null) {
            throw new IllegalArgumentException("Employee ID is required");
        }
        if (punchIn.getLoginLocation() == null || punchIn.getLoginLocation().trim().isEmpty()) {
            throw new IllegalArgumentException("Login location is required");
        }
    }

    private void validateDateRange(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Both start and end dates are required");
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }
    }
    
    public ResponseStructure<List<PunchInDto>> getAllPunchInsForToday() {
        List<PunchInDto> punchIns = dao.getAllPunchInsForToday();

        ResponseStructure<List<PunchInDto>> response = new ResponseStructure<>();
        response.setStatus(200);
        response.setMessage("Fetched all punch-ins for today");
        response.setData(punchIns);

        return response;
    }
    
   
}
