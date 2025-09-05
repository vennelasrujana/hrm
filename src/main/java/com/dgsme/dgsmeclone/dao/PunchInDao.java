package com.dgsme.dgsmeclone.dao;

import com.dgsme.dgsmeclone.dto.PunchInDto;
import com.dgsme.dgsmeclone.repository.PunchInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Component
public class PunchInDao {
    
    private final PunchInRepository repository;
    private static final ZoneId IST_ZONE = ZoneId.of("Asia/Kolkata");

    @Autowired
    public PunchInDao(PunchInRepository repository) {
        this.repository = repository;
    }

    public PunchInDto savePunchIn(PunchInDto punchIn) {
        LocalDate today = LocalDate.now(IST_ZONE);
        LocalTime punchInTime = LocalTime.now(IST_ZONE);

        if (repository.existsByEmployeeIdAndLoginDate(punchIn.getEmployeeId(), today)) {
            throw new IllegalStateException("Employee has already punched in today");
        }

        if (punchInTime.isBefore(LocalTime.of(8, 30)) || punchInTime.isAfter(LocalTime.of(13, 30))) {
            throw new IllegalStateException("Punch-in is only allowed between 8:30 AM and 1:30 PM");
        }

        String attendanceStatus;
        if (punchInTime.isBefore(LocalTime.of(9, 15))) {
            attendanceStatus = "Present";
        } else if (punchInTime.isBefore(LocalTime.of(9, 30))) {
            attendanceStatus = "Late";
        } else {
            attendanceStatus = "Absent";
        }

        punchIn.setStatus(attendanceStatus);
        return repository.save(punchIn);
    }

    public List<PunchInDto> getAllPunchInDetailsByEmployeeId(String employeeId) {
        return repository.findByEmployeeId(employeeId);
    }

    public List<PunchInDto> getPunchInsByEmployeeAndDate(String employeeId, LocalDate date) {
        return repository.findByEmployeeIdAndLoginDate(employeeId, date);
    }

    public Optional<PunchInDto> getLastPunchIn(String employeeId) {
        LocalDate today = LocalDate.now(IST_ZONE);
        List<PunchInDto> punchIns = repository.findByEmployeeIdAndLoginDate(employeeId, today);
        return punchIns.stream()
            .max((p1, p2) -> p1.getLoginTime().compareTo(p2.getLoginTime()));
    }

    public List<PunchInDto> getPunchInsByDateRange(String employeeId, LocalDate startDate, LocalDate endDate) {
        return repository.findByEmployeeIdAndLoginDateBetween(employeeId, startDate, endDate);
    }

    public boolean hasPunchedInToday(String employeeId) {
        return repository.existsByEmployeeIdAndLoginDate(employeeId, LocalDate.now(IST_ZONE));
    }

    public List<PunchInDto> getLatePunchIns(LocalDate date) {
        return repository.findLatePunchIns(date, LocalTime.of(9, 15));
    }

    public List<PunchInDto> getEarlyPunchIns(LocalDate date) {
        return repository.findEarlyPunchIns(date, LocalTime.of(9, 0));
    }

    public List<PunchInDto> getPunchInsByTimeRange(String employeeId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        return repository.findByEmployeeIdAndLoginDateAndLoginTimeBetween(employeeId, date, startTime, endTime);
    }

    public List<PunchInDto> getAllPunchInsForToday() {
        return repository.findAllPunchInsForToday(LocalDate.now(IST_ZONE));
    }

    public void deletePunchIn(String id) {
        repository.deleteByEmployeeId(id);
    }
    
}
