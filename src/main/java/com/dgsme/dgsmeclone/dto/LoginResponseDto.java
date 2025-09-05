package com.dgsme.dgsmeclone.dto;


public class LoginResponseDto {
    private String token;
    private AdminDto admin;
    private String employeeRole;
    private EmployeeLoginCredentialsDto employee;

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }
    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public EmployeeLoginCredentialsDto getEmployee() {
        return employee;
    }
    public void setEmployee(EmployeeLoginCredentialsDto employee) {
        this.employee = employee;
    }
    public AdminDto getAdmin() {
        return admin;
    }
    public void setAdmin(AdminDto admin) {
        this.admin = admin;
    }
}
