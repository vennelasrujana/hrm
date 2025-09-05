package com.dgsme.dgsmeclone.dao;

import com.dgsme.dgsmeclone.dto.PunchOutDto;
import com.dgsme.dgsmeclone.repository.PunchOutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Component
public class PunchOutDao {

    private final PunchOutRepository repository;
    private static final ZoneId IST_ZONE = ZoneId.of("Asia/Kolkata");

    @Autowired
    public PunchOutDao(PunchOutRepository repository) {
        this.repository = repository;
    }

    public PunchOutDto savePunchOut(PunchOutDto punchOut) {
        LocalDate today = LocalDate.now(IST_ZONE);
        LocalTime currentTime = LocalTime.now(IST_ZONE);

        if (repository.existsByEmployeeIdAndLogoutDate(punchOut.getEmployeeId(), today)) {
            throw new IllegalStateException("Employee has already punched out today");
        }

        if (currentTime.isBefore(LocalTime.of(9, 30)) || currentTime.isAfter(LocalTime.of(18, 30))) {
            throw new IllegalStateException("Punch-out is only allowed between 9:30 AM and 6:30 PM");
        }

        return repository.save(punchOut);
    }

    public List<PunchOutDto> getPunchOutsByEmployeeAndDate(String employeeId, LocalDate date) {
        return repository.findByEmployeeIdAndLogoutDate(employeeId, date);
    }

    public Optional<PunchOutDto> getLastPunchOut(String employeeId) {
        LocalDate today = LocalDate.now(IST_ZONE);
        List<PunchOutDto> punchOuts = repository.findByEmployeeIdAndLogoutDate(employeeId, today);
        return punchOuts.stream()
            .max((p1, p2) -> p1.getLogoutTime().compareTo(p2.getLogoutTime()));
    }

    public List<PunchOutDto> getPunchOutsByDateRange(String employeeId, LocalDate startDate, LocalDate endDate) {
        return repository.findByEmployeeIdAndLogoutDateBetween(employeeId, startDate, endDate);
    }

    public boolean hasPunchedOutToday(String employeeId) {
        return repository.existsByEmployeeIdAndLogoutDate(employeeId, LocalDate.now(IST_ZONE));
    }

    public List<PunchOutDto> getEarlyPunchOuts(LocalDate date) {
        return repository.findEarlyPunchOuts(date, LocalTime.of(17, 0));
    }

    public List<PunchOutDto> getLatePunchOuts(LocalDate date) {
        return repository.findLatePunchOuts(date, LocalTime.of(18, 0));
    }

    public List<PunchOutDto> getPunchOutsByTimeRange(String employeeId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        return repository.findByEmployeeIdAndLogoutDateAndLogoutTimeBetween(employeeId, date, startTime, endTime);
    }

    public void deletePunchOut(String id) {
        repository.deleteByEmployeeId(id);
    }
}
