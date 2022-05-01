package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Department {

	@Id
	@GeneratedValue
	private int deptId;
	private String deptName;
	private String deptLocation;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="deptId")
	private Set<Employee> employee=new HashSet<>();
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptLocation() {
		return deptLocation;
	}
	public void setDeptLocation(String deptLocation) {
		this.deptLocation = deptLocation;
	}
	
	public Set<Employee> getEmployee() {
		return employee;
	}
	public void setEmployee(Set<Employee> employee) {
		this.employee = employee;
	}
	@Override
	public String toString() {
		return "Department [deptNo=" + deptId + ", deptName=" + deptName + ", deptLocation=" + deptLocation
				+ ", employee=" + employee + "]";
	}
	
}
