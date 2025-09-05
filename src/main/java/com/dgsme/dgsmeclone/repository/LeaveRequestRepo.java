package com.dgsme.dgsmeclone.repository;

import com.dgsme.dgsmeclone.dto.LeaveRequests;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRequestRepo extends JpaRepository<LeaveRequests, Long> {
	
	  @Transactional
	    @Modifying
	    @Query("UPDATE LeaveRequests lr SET lr.status = :status WHERE lr.employeeId = :employeeId")
	    int updateLeaveRequestStatus(@Param("employeeId") String employeeId, @Param("status") String status);
	  
	  long countByStatus(String status);
	  List<LeaveRequests> findByCreatedAtAfter(ZonedDateTime time);

	  Optional<LeaveRequests> findByEmployeeId(String employeeId);
	  
	
	  List<LeaveRequests> findByEmployeeIdAndStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
	          String employeeId, String status, LocalDate date1, LocalDate date2);

	
	  @Query("SELECT lr FROM LeaveRequests lr WHERE lr.employeeId = :employeeId AND lr.status = 'APPROVED' AND " +
	         "((lr.startDate <= :endDate AND lr.endDate >= :startDate))")
	  List<LeaveRequests> findApprovedByEmployeeIdAndDateRange(@Param("employeeId") String employeeId,
	                                                           @Param("startDate") LocalDate startDate,
	                                                           @Param("endDate") LocalDate endDate);

	
	  List<LeaveRequests> findByStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
	          String status, LocalDate date1, LocalDate date2);

	
	  @Query("SELECT lr FROM LeaveRequests lr WHERE lr.status = 'APPROVED' AND " +
		       "((lr.startDate <= :endDate AND lr.endDate >= :startDate))")
		List<LeaveRequests> findApprovedByDateRange(@Param("startDate") LocalDate startDate,
		                                            @Param("endDate") LocalDate endDate);
	
	  void deleteByEmployeeId(String employeeId);
}