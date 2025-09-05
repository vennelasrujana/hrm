package com.dgsme.dgsmeclone.controller;

import com.dgsme.dgsmeclone.dto.AttendanceResponse;
import com.dgsme.dgsmeclone.repository.AttendanceRepo;
import com.dgsme.dgsmeclone.service.AttendanceService;
import com.dgsme.dgsmeclone.util.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attendance")

public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;
    
    @Autowired
    private AttendanceRepo attendanceRepo;

    @GetMapping("/{employeeId}")
    public ResponseEntity<ResponseStructure<Map<String, Long>>> getAttendance(
            @PathVariable String employeeId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        ResponseStructure<Map<String, Long>> response = attendanceService.getAttendance(employeeId, startDate, endDate);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    
    @GetMapping("/range")
    public List<AttendanceResponse> getAttendanceBetweenDates(
        @RequestParam("start") 
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate start,
        @RequestParam("end")   
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate end
    ) {
        return attendanceRepo.findAttendanceByDateRange(start, end);
    }
    
    @GetMapping("/targetDate")
    public List<AttendanceResponse> getTargetDateDetails(
        @RequestParam("targetDate") 
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate targetDate
    ) {
        return attendanceRepo.findAttendanceByDate(targetDate);
    }


    
}
