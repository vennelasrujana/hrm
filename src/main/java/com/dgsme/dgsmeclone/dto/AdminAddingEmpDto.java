package com.dgsme.dgsmeclone.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDateTime;

@Entity
public class AdminAddingEmpDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(unique = true)
	private String employeeId;
	private String employeeName;
	@Column(unique = true)
    private String employeeEmail;
    private String employeePosition;
    private String employeeDepartment;
    private String employeePhone;
	private String shiftType;
	private String employeeAddress;
    private String employeeGender;
	private String employeeShift;
	private String employeeMaritalStatus;
    private String employeeDOB;
    @Column(name = "join_date")
    private LocalDateTime joinDate;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
	@ManyToOne
    @JoinColumn(name = "company_id")
	@JsonBackReference
    private CompanyInfoDto company;

    public AdminAddingEmpDto() {
	}

	public AdminAddingEmpDto(Long id, String employeeId, String employeeName, String employeeEmail,
			String employeePosition, String employeeDepartment, String employeePhone, String shiftType,
			String employeeAddress, String employeeGender, String employeeShift, String employeeMaritalStatus,
			String employeeDOB, LocalDateTime joinDate, LocalDateTime createdAt, LocalDateTime updatedAt,
			CompanyInfoDto company) {
		this.id = id;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeEmail = employeeEmail;
		this.employeePosition = employeePosition;
		this.employeeDepartment = employeeDepartment;
		this.employeePhone = employeePhone;
		this.shiftType = shiftType;
		this.employeeAddress = employeeAddress;
		this.employeeGender = employeeGender;
		this.employeeShift = employeeShift;
		this.employeeMaritalStatus = employeeMaritalStatus;
		this.employeeDOB = employeeDOB;
		this.joinDate = joinDate;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.company = company;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getShiftType() {
		return shiftType;
	}

	public void setShiftType(String shiftType) {
		this.shiftType = shiftType;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	public String getEmployeeGender() {
		return employeeGender;
	}

	public void setEmployeeGender(String employeeGender) {
		this.employeeGender = employeeGender;
	}

	public String getEmployeeShift() {
		return employeeShift;
	}

	public void setEmployeeShift(String employeeShift) {
		this.employeeShift = employeeShift;
	}

	public String getEmployeeMaritalStatus() {
		return employeeMaritalStatus;
	}

	public void setEmployeeMaritalStatus(String employeeMaritalStatus) {
		this.employeeMaritalStatus = employeeMaritalStatus;
	}

	public String getEmployeeDOB() {
		return employeeDOB;
	}

	public void setEmployeeDOB(String employeeDOB) {
		this.employeeDOB = employeeDOB;
	}

	public LocalDateTime getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDateTime joinDate) {
		this.joinDate = joinDate;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public CompanyInfoDto getCompany() {
		return company;
	}

	public void setCompany(CompanyInfoDto company) {
		this.company = company;
	}

	
} 