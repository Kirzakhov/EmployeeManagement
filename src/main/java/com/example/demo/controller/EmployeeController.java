package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.error.DepartmentNotFoundException;
import com.example.demo.error.EmployeeNotFoundException;
import com.example.demo.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@GetMapping("/director/get/all")
	public List<Employee> getAll(){
		return service.getAll();
	}
	
	@GetMapping("/director/getOne/{empId}")
	public Employee getOne(@PathVariable Integer empId) throws EmployeeNotFoundException {
		return service.getOne(empId);
	}
	
	@GetMapping("/director/getByDate/{date}")
	public List<Employee> getByJoiningDate(@PathVariable @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date){
		return service.getByJoiningDate(date);
	}
	
	@GetMapping("/director/getByDate/from/{from}/to/{to}")
	public List<Employee> getByDateBetween(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from, 
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to){
		return service.getByDateBetween(from, to);
	}
	
	@GetMapping("/director/{managerId}")
	public List<Employee> getByManagerId(@PathVariable Integer managerId){
		return service.getByManagerId(managerId);
	}
	
	@GetMapping("/director/departments")
	public List<Department> getDepartments(){
		return service.getDepartments();
	}
	
	@GetMapping("/director/department/{deptId}")
	public Department getDepartment(@PathVariable Integer deptId) throws DepartmentNotFoundException{
		return service.getDepartment(deptId);
	}
	
	@PostMapping("/director/addDept")
	public Department addDepartment(@RequestBody Department dept) {
		return service.addDepartment(dept);
	}
	
	@PutMapping("/director/updateDept/{deptId}")
	public Department updateDepartment(@PathVariable Integer deptId,@RequestBody Department dept) throws DepartmentNotFoundException {
		return service.updateDepartment(deptId, dept);
	}
	
	@DeleteMapping("/director/deleteDept/{deptId}")
	public String deleteDepartment(@PathVariable Integer deptId) throws DepartmentNotFoundException {
		return service.deleteDepartment(deptId);
	}
	
	@PostMapping("/director/addUser/{deptId}")
	public Employee addEmployee(@PathVariable Integer deptId, @RequestBody Employee emp) throws DepartmentNotFoundException, EmployeeNotFoundException {
		return service.addEmployee(deptId, emp);
	}
	
	@PutMapping("/director/updateUser/{empId}")
	public Employee updateUser(@PathVariable Integer empId, @RequestBody Employee emp) throws EmployeeNotFoundException {
		return service.updateUser(empId, emp);
	}
	
	@PutMapping("/director/update/{empId}")
	public Employee updateSelfByDirector(@PathVariable Integer empId, @RequestBody Employee emp) throws EmployeeNotFoundException {
		return service.updateSelfByDirector(empId, emp);
	}
	
	@DeleteMapping("/director/deleteUser/{empId}")
	public String deleteUser(@PathVariable Integer empId) throws EmployeeNotFoundException {
		return service.deleteUser(empId);
	}
	
	@GetMapping("/manager/getEmployees/{empId}")
	public List<Employee> getEmployees(@PathVariable Integer empId) throws EmployeeNotFoundException{
		return service.getEmployees(empId);
	}
	
	@GetMapping("/manager/getEmployee/{empId}")
	public Employee getEmployee(@PathVariable Integer empId) throws EmployeeNotFoundException {
		return service.getEmployee(empId);
	}
	
	@PostMapping("/manager/{deptId}")
	public Employee addEmployeeByMan(@PathVariable Integer deptId, @RequestBody Employee emp) throws EmployeeNotFoundException, DepartmentNotFoundException {
		return service.addEmployeeByMan(deptId, emp);
	}
	
	@PutMapping("/manager/updateEmp/{empId}")
	public Employee updateEmployee(@PathVariable Integer empId, @RequestBody Employee emp) throws EmployeeNotFoundException {
		return service.updateEmployee(empId, emp);
	}
	
	@PutMapping("/manager/update/{empId}")
	public Employee updateSelfByManager(@PathVariable Integer empId, @RequestBody Employee emp) throws EmployeeNotFoundException {
		return service.updateSelfByManager(empId, emp);
	}
	
	@DeleteMapping("/manager/delete/{empId}")
	public String deleteEmployee(@PathVariable Integer empId) throws EmployeeNotFoundException {
		return service.deleteEmployee(empId);
	}
	
	@PutMapping("/employee/update/{empId}")
	public Employee updateEmp(@PathVariable Integer empId, @RequestBody Employee emp) throws EmployeeNotFoundException {
		return service.updateSelfByUser(empId, emp);
	}
	
	
}
