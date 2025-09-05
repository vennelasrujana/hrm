package com.dgsme.dgsmeclone.dto;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EmployeeWithPunchDataDto {
    private String employeeId;
    private String employeeName;
    private String employeeEmail;
    private String employeePosition;
    private String employeeDepartment;
    private String employeePhone;
    private LocalDateTime joinDate;
    
    private LocalDate loginDate;
    private LocalTime loginTime;
    private String loginLocation;
    
    private LocalDate logoutDate;
    private LocalTime logoutTime;
    private String logoutLocation;

  
    public EmployeeWithPunchDataDto(AdminAddingEmpDto employee, PunchInDto punchIn, PunchOutDto punchOut) {
        this.employeeId = employee.getEmployeeId();
        this.employeeName = employee.getEmployeeName();
        this.employeeEmail = employee.getEmployeeEmail();
        this.employeePosition = employee.getEmployeePosition();
        this.employeeDepartment = employee.getEmployeeDepartment();
        this.employeePhone = employee.getEmployeePhone();
        this.joinDate = employee.getJoinDate();
        
        if (punchIn != null) {
            this.loginDate = punchIn.getLoginDate();
            this.loginTime = punchIn.getLoginTime();
            this.loginLocation = punchIn.getLoginLocation();
        }
        
        if (punchOut != null) {
            this.logoutDate = punchOut.getLogoutDate();
            this.logoutTime = punchOut.getLogoutTime();
            this.logoutLocation = punchOut.getLogoutLocation();
        }
    }

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getEmployeePosition() {
		return employeePosition;
	}

	public void setEmployeePosition(String employeePosition) {
		this.employeePosition = employeePosition;
	}

	public String getEmployeeDepartment() {
		return employeeDepartment;
	}

	public void setEmployeeDepartment(String employeeDepartment) {
		this.employeeDepartment = employeeDepartment;
	}

	public String getEmployeePhone() {
		return employeePhone;
	}

	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}

	public LocalDateTime getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDateTime joinDate) {
		this.joinDate = joinDate;
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

   
}

