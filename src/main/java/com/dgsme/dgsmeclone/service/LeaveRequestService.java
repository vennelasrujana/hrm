package com.dgsme.dgsmeclone.service;

import com.dgsme.dgsmeclone.dao.LeaveRequestDao;
import com.dgsme.dgsmeclone.dto.LeaveRequests;
import com.dgsme.dgsmeclone.util.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LeaveRequestService {

    @Autowired
    private LeaveRequestDao leaveRequestDao;

    public ResponseStructure<LeaveRequests> createLeaveRequest(LeaveRequests leaveRequest) {
        LeaveRequests saved = leaveRequestDao.saveLeaveRequest(leaveRequest);

        ResponseStructure<LeaveRequests> response = new ResponseStructure<>();
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("Leave request created successfully.");
        response.setData(saved);
        return response;
    }

    public ResponseStructure<List<LeaveRequests>> getAllLeaveRequests() {
        List<LeaveRequests> leaveRequests = leaveRequestDao.findAllLeaveRequests();

        ResponseStructure<List<LeaveRequests>> response = new ResponseStructure<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Fetched all leave requests.");
        response.setData(leaveRequests);
        return response;
    }

    public ResponseStructure<LeaveRequests> getLeaveRequestByEmployeeId(String id) {
        LeaveRequests leaveRequest = leaveRequestDao.findLeaveRequestById(id).orElse(null);

        ResponseStructure<LeaveRequests> response = new ResponseStructure<>();
        if (leaveRequest != null) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Leave request found.");
            response.setData(leaveRequest);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("Leave request not found.");
            response.setData(null);
        }
        return response;
    }

    public ResponseStructure<String> updateLeaveStatus(String employeeId, String status) {
        int updatedCount = leaveRequestDao.updateLeaveStatus(employeeId, status);

        ResponseStructure<String> response = new ResponseStructure<>();
        if (updatedCount > 0) {
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Leave status updated successfully.");
            response.setData("Updated");
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setMessage("Leave request not found for employeeId: " + employeeId);
            response.setData("Not updated");
        }
        return response;
    }

    public ResponseStructure<Map<String, Long>> getRecentLeaveRequestCounts() {
        ZonedDateTime thirtyHoursAgo = ZonedDateTime.now().minusHours(30);
        List<LeaveRequests> recentRequests = leaveRequestDao.findRecentLeaveRequests(thirtyHoursAgo);

        long total = recentRequests.size();
        long pending = recentRequests.stream().filter(req -> "PENDING".equalsIgnoreCase(req.getStatus())).count();
        long approved = recentRequests.stream().filter(req -> "APPROVED".equalsIgnoreCase(req.getStatus())).count();
        long rejected = recentRequests.stream().filter(req -> "REJECTED".equalsIgnoreCase(req.getStatus())).count();

        Map<String, Long> counts = new HashMap<>();
        counts.put("total", total);
        counts.put("pending", pending);
        counts.put("approved", approved);
        counts.put("rejected", rejected);

        ResponseStructure<Map<String, Long>> response = new ResponseStructure<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Recent leave request counts fetched.");
        response.setData(counts);
        return response;
    }
    
    public ResponseStructure<List<LeaveRequests>> getApprovedLeavesForEmpToday(String empId, LocalDate today) {
        List<LeaveRequests> list = leaveRequestDao.getApprovedLeavesForEmpToday(empId, today);
        return wrapListResponse(list, "Approved leaves for employee today fetched.");
    }

    public ResponseStructure<List<LeaveRequests>> getApprovedLeavesForEmpByRange(String empId, LocalDate start, LocalDate end) {
        List<LeaveRequests> list = leaveRequestDao.getApprovedLeavesForEmpByDateRange(empId, start, end);
        return wrapListResponse(list, "Approved leaves for employee in date range fetched.");
    }

    public ResponseStructure<List<LeaveRequests>> getApprovedLeavesForAllToday(LocalDate today) {
        List<LeaveRequests> list = leaveRequestDao.getApprovedLeavesForAllToday(today);
        return wrapListResponse(list, "Approved leaves for all employees today fetched.");
    }

    public ResponseStructure<List<LeaveRequests>> getApprovedLeavesForAllByRange(LocalDate start, LocalDate end) {
        List<LeaveRequests> list = leaveRequestDao.getApprovedLeavesForAllByDateRange(start, end);
        return wrapListResponse(list, "Approved leaves for all employees in date range fetched.");
    }

    private ResponseStructure<List<LeaveRequests>> wrapListResponse(List<LeaveRequests> list, String message) {
        ResponseStructure<List<LeaveRequests>> response = new ResponseStructure<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage(message);
        response.setData(list);
        return response;
    }
}
