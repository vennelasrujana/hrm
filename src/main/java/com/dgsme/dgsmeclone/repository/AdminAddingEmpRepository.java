package com.dgsme.dgsmeclone.repository;

import com.dgsme.dgsmeclone.dto.AdminAddingEmpDto;
// import com.dgsme.dgsmeclone.dto.CompanyInfoDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AdminAddingEmpRepository extends JpaRepository<AdminAddingEmpDto, Long> {
    Optional<AdminAddingEmpDto> findByEmployeeEmail(String email);
    
    List<AdminAddingEmpDto> findByEmployeeDepartment(String department);
    
    List<AdminAddingEmpDto> findByEmployeePosition(String position);
    
    Optional<AdminAddingEmpDto> findByEmployeeId(String id);

    Optional<AdminAddingEmpDto> findByEmployeeName(String name);



    void deleteEmployeeByEmployeeId(String employeeId);
    
    @Query("SELECT e FROM AdminAddingEmpDto e WHERE e.employeeName LIKE %:name%")
    List<AdminAddingEmpDto> findByEmployeeNameContaining(@Param("name") String name);
    
    @Query("SELECT e FROM AdminAddingEmpDto e WHERE e.joinDate BETWEEN :startDate AND :endDate")
    List<AdminAddingEmpDto> findByJoinDateBetween(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);
    
  
    @Query("SELECT e, pIn, pOut FROM AdminAddingEmpDto e " +
            "LEFT JOIN PunchInDto pIn ON e.employeeId = pIn.employeeId " +
            "LEFT JOIN PunchOutDto pOut ON e.employeeId = pOut.employeeId " +
            "WHERE e.employeeId = :employeeId")
    List<Object[]> findEmployeeDetailsWithPunchInOut(@Param("employeeId") int employeeId);
    
    
    @Query("SELECT COUNT(e) FROM AdminAddingEmpDto e")
    Long countTotalEmployees();


} 