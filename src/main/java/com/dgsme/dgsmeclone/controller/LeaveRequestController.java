package com.dgsme.dgsmeclone.controller;

import com.dgsme.dgsmeclone.dto.LeaveRequests;
import com.dgsme.dgsmeclone.service.LeaveRequestService;
import com.dgsme.dgsmeclone.util.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/leave-requests")

public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    @PostMapping
    public ResponseEntity<ResponseStructure<LeaveRequests>> createLeaveRequest(@RequestBody LeaveRequests leaveRequest) {
        ResponseStructure<LeaveRequests> response = leaveRequestService.createLeaveRequest(leaveRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<LeaveRequests>>> getAllLeaveRequests() {
        ResponseStructure<List<LeaveRequests>> response = leaveRequestService.getAllLeaveRequests();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<LeaveRequests>> getLeaveRequestById(@PathVariable String id) {
        ResponseStructure<LeaveRequests> response = leaveRequestService.getLeaveRequestByEmployeeId(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/update-status")
    public ResponseEntity<ResponseStructure<String>> updateLeaveStatus(@RequestBody Map<String, Object> request) {
    	String employeeId = request.get("employeeId").toString();

        String status = request.get("status").toString();

        ResponseStructure<String> response = leaveRequestService.updateLeaveStatus(employeeId, status);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/recent-counts")
    public ResponseEntity<ResponseStructure<Map<String, Long>>> getRecentLeaveRequestCounts() {
        ResponseStructure<Map<String, Long>> response = leaveRequestService.getRecentLeaveRequestCounts();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    
    @GetMapping("/approved/emp/{empId}/today")
    public ResponseEntity<ResponseStructure<List<LeaveRequests>>> getApprovedForEmpToday(@PathVariable String empId) {
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Kolkata"));
        return ResponseEntity.ok(leaveRequestService.getApprovedLeavesForEmpToday(empId, today));
    }

    @GetMapping("/approved/emp/{empId}/range")
    public ResponseEntity<ResponseStructure<List<LeaveRequests>>> getApprovedForEmpByRange(
            @PathVariable String empId,
            @RequestParam("start") String startDate,
            @RequestParam("end") String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return ResponseEntity.ok(leaveRequestService.getApprovedLeavesForEmpByRange(empId, start, end));
    }

    @GetMapping("/approved/all/today")
    public ResponseEntity<ResponseStructure<List<LeaveRequests>>> getApprovedForAllToday() {
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Kolkata"));
        return ResponseEntity.ok(leaveRequestService.getApprovedLeavesForAllToday(today));
    }

    @GetMapping("/approved/all/range")
    public ResponseEntity<ResponseStructure<List<LeaveRequests>>> getApprovedForAllByRange(
            @RequestParam("start") String startDate,
            @RequestParam("end") String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return ResponseEntity.ok(leaveRequestService.getApprovedLeavesForAllByRange(start, end));
    }
}
