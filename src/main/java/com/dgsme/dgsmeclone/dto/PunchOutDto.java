package com.dgsme.dgsmeclone.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PunchOutDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String employeeId;
    private LocalDate logoutDate;
    private LocalTime logoutTime;
    private String logoutLocation;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getLogoutDate() {
        return logoutDate;
    }

    public void setLogoutDate(LocalDate logoutDate) {
        this.logoutDate = logoutDate;
    }

    public LocalTime getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(LocalTime logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getLogoutLocation() {
        return logoutLocation;
    }

    public void setLogoutLocation(String logoutLocation) {
        this.logoutLocation = logoutLocation;
    }
} 