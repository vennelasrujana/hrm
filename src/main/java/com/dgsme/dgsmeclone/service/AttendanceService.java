package com.dgsme.dgsmeclone.service;

import com.dgsme.dgsmeclone.dao.AttendanceDao;
import com.dgsme.dgsmeclone.dto.PunchInDto;
import com.dgsme.dgsmeclone.dto.PunchOutDto;
import com.dgsme.dgsmeclone.repository.PunchInRepository;
import com.dgsme.dgsmeclone.repository.PunchOutRepository;
import com.dgsme.dgsmeclone.util.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceDao attendanceDao;
    
    @Autowired
    private PunchInRepository punchInRepository;

    @Autowired
    private PunchOutRepository punchOutRepository;

    public ResponseStructure<Map<String, Long>> getAttendance(String employeeId, LocalDate startDate, LocalDate endDate) {
        Map<String, Long> attendance = attendanceDao.calculateAttendance(employeeId, startDate, endDate);

        ResponseStructure<Map<String, Long>> response = new ResponseStructure<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Attendance data fetched successfully.");
        response.setData(attendance);
        return response;
    }
    


}
