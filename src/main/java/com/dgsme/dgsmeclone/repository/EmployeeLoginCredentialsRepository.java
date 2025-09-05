package com.dgsme.dgsmeclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dgsme.dgsmeclone.dto.EmployeeLoginCredentialsDto;

import jakarta.transaction.Transactional;

import java.util.Optional;

public interface EmployeeLoginCredentialsRepository extends JpaRepository<EmployeeLoginCredentialsDto, Long> {
    Optional<EmployeeLoginCredentialsDto> findByEmployeeEmail(String employeeEmail);

    @Transactional
    void deleteByEmployeeId(String employeeId);
}
