package com.dgsme.dgsmeclone.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceResponse {
    private String empId;
    private String empName;
    private String empDept;
    private LocalDate loginDate;
    private LocalTime loginTime;
    private String loginLocation;
    private LocalDate logoutDate;
    private LocalTime logoutTime;
    private String logoutLocation;
    private String status;

    public AttendanceResponse(String empId, String empName, String empDept,
                              LocalDate loginDate, LocalTime loginTime, String loginLocation, String status,
                              LocalDate logoutDate, LocalTime logoutTime, String logoutLocation) {
        this.empId         = empId;
        this.empName       = empName;
        this.empDept       = empDept;
        this.loginDate     = loginDate;
        this.loginTime     = loginTime;
        this.loginLocation = loginLocation;
        this.status        = status;
        this.logoutDate     = logoutDate;
        this.logoutTime     = logoutTime;
        this.logoutLocation = logoutLocation;
    }

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpDept() {
		return empDept;
	}

	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}

	public LocalDate getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(LocalDate loginDate) {
		this.loginDate = loginDate;
	}

	public LocalTime getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(LocalTime loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginLocation() {
		return loginLocation;
	}

	public void setLoginLocation(String loginLocation) {
		this.loginLocation = loginLocation;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    
}