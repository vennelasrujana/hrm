package com.dgsme.dgsmeclone.repository;

import com.dgsme.dgsmeclone.dto.PunchInDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface PunchInRepository extends JpaRepository<PunchInDto, Long> {

    List<PunchInDto> findByEmployeeIdAndLoginDate(String employeeId, LocalDate date);

    List<PunchInDto> findByEmployeeId(String employeeId);

    List<PunchInDto> findByLoginDate(LocalDate date);

    @Query("SELECT p FROM PunchInDto p WHERE p.employeeId = :employeeId AND p.loginDate BETWEEN :startDate AND :endDate")
    List<PunchInDto> findByEmployeeIdAndLoginDateBetween(
            @Param("employeeId") String employeeId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Query("SELECT p FROM PunchInDto p WHERE p.loginDate = :date AND p.loginTime > :time")
    List<PunchInDto> findLatePunchIns(
            @Param("date") LocalDate date,
            @Param("time") LocalTime time);

    @Query("SELECT p FROM PunchInDto p WHERE p.loginDate = :date AND p.loginTime < :time")
    List<PunchInDto> findEarlyPunchIns(
            @Param("date") LocalDate date,
            @Param("time") LocalTime time);

    @Query("SELECT p FROM PunchInDto p WHERE p.employeeId = :employeeId AND p.loginDate = :date AND p.loginTime BETWEEN :startTime AND :endTime")
    List<PunchInDto> findByEmployeeIdAndLoginDateAndLoginTimeBetween(
            @Param("employeeId") String employeeId,
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime);

    @Query("SELECT COUNT(p) > 0 FROM PunchInDto p WHERE p.employeeId = :employeeId AND p.loginDate = :date")
    boolean existsByEmployeeIdAndLoginDate(
            @Param("employeeId") String employeeId,
            @Param("date") LocalDate date);

 
    @Query("SELECT COUNT(p) FROM PunchInDto p WHERE p.loginDate = CURRENT_DATE AND p.loginTime > :lateTime")
    Long countLatePunchIns(@Param("lateTime") LocalTime lateTime);

   
    @Query("SELECT COUNT(p) FROM PunchInDto p WHERE p.loginDate = CURRENT_DATE")
    Long countTodayPunchIns();
    
    
    
    @Query("SELECT p FROM PunchInDto p WHERE p.loginDate = :date")
    List<PunchInDto> findAllPunchInsForToday(@Param("date") LocalDate date);
    
    @Query("SELECT p FROM PunchInDto p WHERE p.loginDate BETWEEN :startDate AND :endDate")
    List<PunchInDto> findAllByLoginDateBetween(LocalDate startDate,LocalDate endDate);

    void deleteByEmployeeId(String employeeId);
}