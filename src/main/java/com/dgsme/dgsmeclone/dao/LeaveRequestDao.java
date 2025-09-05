package com.dgsme.dgsmeclone.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dgsme.dgsmeclone.dto.LeaveRequests;
import com.dgsme.dgsmeclone.repository.LeaveRequestRepo;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class LeaveRequestDao {

    @Autowired
    private LeaveRequestRepo leaveRequestRepo;

    public LeaveRequests saveLeaveRequest(LeaveRequests leaveRequest) {
        return leaveRequestRepo.save(leaveRequest);
    }

    public List<LeaveRequests> findAllLeaveRequests() {
        return leaveRequestRepo.findAll();
    }

    public Optional<LeaveRequests> findLeaveRequestById(String id) {
        return leaveRequestRepo.findByEmployeeId(id);
    }
    
    public int updateLeaveStatus(String employeeId, String status) {
        return leaveRequestRepo.updateLeaveRequestStatus(employeeId, status);
    }
    
    public long countAllLeaveRequests() {
        return leaveRequestRepo.count();
    }

    public List<LeaveRequests> findRecentLeaveRequests(ZonedDateTime afterTime) {
        return leaveRequestRepo.findByCreatedAtAfter(afterTime);
    }
    
    public List<LeaveRequests> getApprovedLeavesForEmpToday(String empId, LocalDate today) {
        return leaveRequestRepo.findByEmployeeIdAndStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                empId, "APPROVED", today, today);
    }

    public List<LeaveRequests> getApprovedLeavesForEmpByDateRange(String empId, LocalDate start, LocalDate end) {
        return leaveRequestRepo.findApprovedByEmployeeIdAndDateRange(empId, start, end);
    }

    public List<LeaveRequests> getApprovedLeavesForAllToday(LocalDate today) {
        return leaveRequestRepo.findByStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                "APPROVED", today, today);
    }
    
    public List<LeaveRequests> getApprovedLeavesForAllByDateRange(LocalDate start, LocalDate end) {
        return leaveRequestRepo.findApprovedByDateRange(start, end);
    }

    public void deleteLeaveRequest(String id) {
        leaveRequestRepo.deleteByEmployeeId(id);
    }

    
}
