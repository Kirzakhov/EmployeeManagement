package com.example.demo.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Employee {

	@Id
	@GeneratedValue(generator = "seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name="seq", initialValue = 1000)
	private Integer empId;
	@NotNull
	private String empName;
	private Double empSalary;
	private LocalDate joiningDate;
	@Column(unique = true)
	@Email
	private String empEmailId;
	@Range(min = 21, message = "Employee age cannot be less than 21 years")
	private Integer empAge;
	@Column(unique = true)
	@Length(min=10, max=13, message="Cell number cannot be less than 10 characters")
	private String empCellNo;
	@Length(min=3, message="Address cannot be less than 3 characters")
	private String empAddress;
	private EmployeeDesignation empDesignation;
	private Float empExperience;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="deptId")
	@JsonIgnore
	private Department empDepartment;
	@Column(unique = true)
	private String username;
	private String password;
	private Integer managerId;
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Double getEmpSalary() {
		return empSalary;
	}
	public void setEmpSalary(Double empSalary) {
		this.empSalary = empSalary;
	}
	public String getEmpEmailId() {
		return empEmailId;
	}
	public void setEmpEmailId(String empEmailId) {
		this.empEmailId = empEmailId;
	}
	public Integer getEmpAge() {
		return empAge;
	}
	public void setEmpAge(Integer empAge) {
		this.empAge = empAge;
	}
	public String getEmpCellNo() {
		return empCellNo;
	}
	public void setEmpCellNo(String empCellNo) {
		this.empCellNo = empCellNo;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}
	public EmployeeDesignation getEmpDesignation() {
		return empDesignation;
	}
	public void setEmpDesignation(EmployeeDesignation empDesignation) {
		this.empDesignation = empDesignation;
	}
	public Float getEmpExperience() {
		return empExperience;
	}
	public void setEmpExperience(Float empExperience) {
		this.empExperience = empExperience;
	}
	public Department getEmpDepartment() {
		return empDepartment;
	}
	public void setEmpDepartment(Department empDepartment) {
		this.empDepartment = empDepartment;
	}
	public LocalDate getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empSalary=" + empSalary + ", joiningDate="
				+ joiningDate + ", empEmailId=" + empEmailId + ", empAge=" + empAge + ", empCellNo=" + empCellNo
				+ ", empAddress=" + empAddress + ", empDesignation=" + empDesignation + ", empExperience="
				+ empExperience + ", empDepartment=" + empDepartment + ", username=" + username + ", password="
				+ password + ", managerId=" + managerId + "]";
	}
	
	
	
}
