package com.dgsme.dgsmeclone.dao;

import com.dgsme.dgsmeclone.dto.LeaveRequests;

import com.dgsme.dgsmeclone.dto.PunchInDto;
import com.dgsme.dgsmeclone.repository.LeaveRequestRepo;
import com.dgsme.dgsmeclone.repository.PunchInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AttendanceDao {

    @Autowired
    private PunchInRepository punchInRepo;

    @Autowired
    private LeaveRequestRepo leaveRequestRepo;

    public Map<String, Long> calculateAttendance(String employeeId, LocalDate startDate, LocalDate endDate) {
    // Step 1: Count only working days (Mon–Fri)
    long totalDays = startDate.datesUntil(endDate.plusDays(1))
            .filter(date -> !(date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7)) // skip Sat(6) & Sun(7)
            .count();

    // Step 2: Get punch-in records
    List<PunchInDto> punchIns = punchInRepo.findByEmployeeIdAndLoginDateBetween(employeeId, startDate, endDate);
    long presentDays = punchIns.stream()
            .map(PunchInDto::getLoginDate)
            .filter(date -> !(date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7)) // skip weekends
            .distinct()
            .count();

    // Step 3: Get approved leave days
    List<LeaveRequests> leaves = leaveRequestRepo.findByEmployeeIdAndStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            employeeId, "APPROVED", endDate, startDate);

    long leaveDays = leaves.stream()
            .flatMap(lr -> lr.getStartDate().datesUntil(lr.getEndDate().plusDays(1)))
            .filter(date -> !date.isBefore(startDate) && !date.isAfter(endDate))
            .filter(date -> !(date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7)) // skip weekends
            .distinct()
            .count();

    // Step 4: Calculate absents
    long absentDays = totalDays - presentDays - leaveDays;

    Map<String, Long> result = new HashMap<>();
    result.put("totalDays", totalDays);
    result.put("presentDays", presentDays);
    result.put("leaveDays", leaveDays);
    result.put("absentDays", absentDays);

    return result;
}

}
