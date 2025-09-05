package com.dgsme.dgsmeclone.repository;

import com.dgsme.dgsmeclone.dto.DepartmentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentDto, Long> {
    Optional<DepartmentDto> findByEmployeeId(String employeeId);
    
    List<DepartmentDto> findByEmployeeDepartment(String employeeDept);
    
    List<DepartmentDto> findByManagerId(Long managerId);
    
    @Query("SELECT d FROM DepartmentDto d WHERE d.employeeName LIKE %:name%")
    List<DepartmentDto> findByEmpNameContaining(@Param("name") String name);
    
    @Query("SELECT d FROM DepartmentDto d WHERE d.employeeId = :empId AND d.managerId = :managerId")
    Optional<DepartmentDto> findByEmpIdAndManagerId(@Param("empId") Long empId, @Param("managerId") Long managerId);

    void deleteByEmployeeId(String employeeId);

} 