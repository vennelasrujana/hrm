package com.dgsme.dgsmeclone.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dgsme.dgsmeclone.dto.AttendanceResponse;
import com.dgsme.dgsmeclone.dto.PunchInDto;
@Repository
public interface AttendanceRepo extends JpaRepository<PunchInDto, Long> {

	@Query("""
		    SELECT new com.dgsme.dgsmeclone.dto.AttendanceResponse(
		        d.employeeId,
		        d.employeeName,
		        d.employeeDepartment,
		        pi.loginDate,
		        pi.loginTime,
		        pi.loginLocation,
		        pi.status,
		        po.logoutDate,
		        po.logoutTime,
		        po.logoutLocation
		    )
		    FROM DepartmentDto d
		    LEFT JOIN PunchInDto pi ON d.employeeId = pi.employeeId
		    LEFT JOIN PunchOutDto po ON d.employeeId = po.employeeId
		    WHERE (pi.loginDate BETWEEN :startDate AND :endDate)
		       OR (po.logoutDate BETWEEN :startDate AND :endDate)
		""")
		List<AttendanceResponse> findAttendanceByDateRange(
		    @Param("startDate") LocalDate startDate,
		    @Param("endDate") LocalDate endDate
		);

	
	@Query("""
		    SELECT new com.dgsme.dgsmeclone.dto.AttendanceResponse(
		        d.employeeId,
		        d.employeeName,
		        d.employeeDepartment,
		        pi.loginDate,
		        pi.loginTime,
		        pi.loginLocation,
		        pi.status,
		        po.logoutDate,
		        po.logoutTime,
		        po.logoutLocation
		    )
		    FROM DepartmentDto d
		    LEFT JOIN PunchInDto pi ON d.employeeId = pi.employeeId
		    LEFT JOIN PunchOutDto po ON d.employeeId = po.employeeId
		    WHERE pi.loginDate = :targetDate
		       OR po.logoutDate = :targetDate
		    """)
		List<AttendanceResponse> findAttendanceByDate(
		    @Param("targetDate") LocalDate targetDate
		);

}


