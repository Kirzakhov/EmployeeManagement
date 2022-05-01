package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeDesignation;
import com.example.demo.error.DepartmentNotFoundException;
import com.example.demo.error.EmployeeNotFoundException;
import com.example.demo.repository.DepartmentRepo;
import com.example.demo.repository.EmployeeRepo;

@Service
public class EmployeeService implements UserDetailsService{
	@Autowired
	private EmployeeRepo repo;
	
	@Autowired
	private DepartmentRepo drepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee e = repo.findByUsername(username);
		if(e==null)
			throw new UsernameNotFoundException("Employee not found");
		return new EmployeePrincipal(e);
	}

	public Employee addEmployee(int deptId, Employee emp) throws DepartmentNotFoundException, EmployeeNotFoundException {
		
		if(!drepo.existsById(deptId))
			throw new DepartmentNotFoundException("Selected Department Not Found");
		else if(!repo.existsById(emp.getManagerId()) || (!repo.findById(emp.getManagerId()).get().getEmpDesignation().equals(EmployeeDesignation.MANAGER) && emp.getManagerId()!=999))
			throw new EmployeeNotFoundException("Manager with given id not found");
		else {
			Department d = drepo.findById(deptId).get();
			if(repo.findById(emp.getManagerId()).get().getEmpDesignation().equals(EmployeeDesignation.MANAGER))
				emp.setEmpDesignation(EmployeeDesignation.EMPLOYEE);
			else if(emp.getManagerId()==999)
				emp.setEmpDesignation(EmployeeDesignation.MANAGER);
			emp.setPassword(passwordEncoder.encode(emp.getPassword()));
			emp.setEmpDepartment(d);
			emp.setJoiningDate(LocalDate.now());
			d.getEmployee().add(emp);
			return repo.save(emp);
		}
	}

	public List<Employee> getAll() {
		
		return repo.findAll();
	}

	public List<Employee> getEmployees(int empId) throws EmployeeNotFoundException {
		if(!repo.existsById(empId) || (!repo.findById(empId).get().getEmpDesignation().equals(EmployeeDesignation.MANAGER)))
			throw new EmployeeNotFoundException("Employee Not Found");
		else
			return repo.findByManagerId(empId);
	}

	public Employee updateUser(int empId, Employee emp) throws EmployeeNotFoundException {
		if(!repo.existsById(empId)) {
			throw new EmployeeNotFoundException("Employee not found");
		}
		else {
			Employee e = repo.findById(empId).get();
			if(Objects.nonNull(emp.getEmpName()) || !"".equals(emp.getEmpName()))
				e.setEmpName(emp.getEmpName());
			if(Objects.nonNull(emp.getEmpSalary()))
				e.setEmpSalary(emp.getEmpSalary());
			if(Objects.nonNull(emp.getEmpEmailId()) || !"".equals(emp.getEmpEmailId()))
				e.setEmpEmailId(emp.getEmpEmailId());
			if(Objects.nonNull(emp.getEmpCellNo()) || !"".equals(emp.getEmpCellNo()))
				e.setEmpCellNo(emp.getEmpCellNo());
			if(Objects.nonNull(emp.getEmpAddress()) || !"".equals(emp.getEmpAddress()))
				e.setEmpAddress(emp.getEmpAddress());
			if(Objects.nonNull(emp.getEmpExperience()))
				e.setEmpExperience(emp.getEmpExperience());
			return repo.save(e);
		}
		
	}

	public String deleteUser(int empId) throws EmployeeNotFoundException {
		if(!repo.existsById(empId) || empId==999)
			throw new EmployeeNotFoundException("Employee Not Found!!!");
		else {
			repo.deleteById(empId);
			return "Record Deleted";
		}
	}

	public Employee updateEmployee(int empId, Employee emp) throws EmployeeNotFoundException {
		if(!repo.existsById(empId) || repo.findById(empId).get().getEmpDesignation().equals(EmployeeDesignation.MANAGER) || empId==999)
			throw new EmployeeNotFoundException("Employee not found");
		else {
			Employee e = repo.findById(empId).get();
			if(Objects.nonNull(emp.getEmpName()) || !"".equals(emp.getEmpName()))
				e.setEmpName(emp.getEmpName());
			if(Objects.nonNull(emp.getEmpSalary()))
				e.setEmpSalary(emp.getEmpSalary());
			if(Objects.nonNull(emp.getEmpEmailId()) || !"".equals(emp.getEmpEmailId()))
				e.setEmpEmailId(emp.getEmpEmailId());
			if(Objects.nonNull(emp.getEmpCellNo()) || !"".equals(emp.getEmpCellNo()))
				e.setEmpCellNo(emp.getEmpCellNo());
			if(Objects.nonNull(emp.getEmpAddress()) || !"".equals(emp.getEmpAddress()))
				e.setEmpAddress(emp.getEmpAddress());
			if(Objects.nonNull(emp.getEmpExperience()))
				e.setEmpExperience(emp.getEmpExperience());
			return repo.save(e);
		}
	}

	public String deleteEmployee(int empId) throws EmployeeNotFoundException {
		if(!repo.existsById(empId) || repo.findById(empId).get().getEmpDesignation().equals(EmployeeDesignation.MANAGER) || empId==999)
			throw new EmployeeNotFoundException("Employee not found");
		else {
			repo.deleteById(empId);
			return "Record Deleted";
		}
	}

	public Department updateDepartment(int deptId, Department dept) throws DepartmentNotFoundException {
		
		if(!drepo.existsById(deptId))
			throw new DepartmentNotFoundException("Department Not Found!!!");
		else {
			Department d = drepo.findById(deptId).get();
			if(Objects.nonNull(dept.getDeptName()) || !"".equals(dept.getDeptName()))
				d.setDeptName(dept.getDeptName());
			if(Objects.nonNull(dept.getDeptLocation()) || !"".equals(dept.getDeptLocation()))
				d.setDeptLocation(dept.getDeptLocation());
			return drepo.save(d);
		}
			
	}

	public String deleteDepartment(int deptId) throws DepartmentNotFoundException {
		if(!drepo.existsById(deptId))
			throw new DepartmentNotFoundException("Department Not Found!!!");
		else {
			drepo.deleteById(deptId);
			return "Record Deleted";
		}
	}

	public Department getDepartment(int deptId) throws DepartmentNotFoundException {
		if(!drepo.existsById(deptId))
			throw new DepartmentNotFoundException("Department Not Found!!!");
		else
			return drepo.findById(deptId).get();
	}

	public List<Department> getDepartments() {
		return drepo.findAll();
	}

	public Department addDepartment(Department dept) {
	
		return drepo.save(dept);
	}

	public List<Employee> getByManagerId(int managerId) {
		
		return repo.findByManagerId(managerId);
	}

	public Employee addEmployeeByMan(int deptId, Employee emp) throws EmployeeNotFoundException, DepartmentNotFoundException {
		Department d = drepo.findById(deptId).get();
		if(d==null)
			throw new DepartmentNotFoundException("Selected Department Not Found");
		else if(!repo.existsById(emp.getManagerId()) || !repo.findById(emp.getManagerId()).get().getEmpDesignation().equals(EmployeeDesignation.MANAGER) || emp.getManagerId()==999)
			throw new EmployeeNotFoundException("Employee not found");
		else {
			emp.setEmpDesignation(EmployeeDesignation.EMPLOYEE);
			emp.setEmpDepartment(d);
			emp.setJoiningDate(LocalDate.now());
			emp.setPassword(passwordEncoder.encode(emp.getPassword()));
			d.getEmployee().add(emp);
			return repo.save(emp);
		}
	}

	public Employee updateSelfByUser(int empId, Employee emp) throws EmployeeNotFoundException {
		if(!repo.existsById(empId) || repo.findById(empId).get().getEmpDesignation().equals(EmployeeDesignation.MANAGER) || empId==999)
			throw new EmployeeNotFoundException("Employee not found");
		else {
			Employee e = repo.findById(empId).get();
			if(Objects.nonNull(emp.getEmpName()) || !"".equals(emp.getEmpName()))
				e.setEmpName(emp.getEmpName());
			if(Objects.nonNull(emp.getEmpSalary()))
				e.setEmpSalary(emp.getEmpSalary());
			if(Objects.nonNull(emp.getEmpEmailId()) || !"".equals(emp.getEmpEmailId()))
				e.setEmpEmailId(emp.getEmpEmailId());
			if(Objects.nonNull(emp.getEmpCellNo()) || !"".equals(emp.getEmpCellNo()))
				e.setEmpCellNo(emp.getEmpCellNo());
			if(Objects.nonNull(emp.getEmpAddress()) || !"".equals(emp.getEmpAddress()))
				e.setEmpAddress(emp.getEmpAddress());
			if(Objects.nonNull(emp.getEmpExperience()))
				e.setEmpExperience(emp.getEmpExperience());
			if(Objects.nonNull(emp.getUsername()) || !"".equals(emp.getUsername()))
				e.setUsername(emp.getUsername());
			if(Objects.nonNull(emp.getPassword()) || !"".equals(emp.getPassword()))
				e.setPassword(passwordEncoder.encode(emp.getPassword()));
			return repo.save(e);
		}
	}

	public Employee updateSelfByManager(int empId, Employee emp) throws EmployeeNotFoundException {
		if(!repo.existsById(empId) || repo.findById(empId).get().getEmpDesignation().equals(EmployeeDesignation.EMPLOYEE) || empId==999)
			throw new EmployeeNotFoundException("Employee not found");
		else {
			Employee e = repo.findById(empId).get();
			if(Objects.nonNull(emp.getEmpName()) || !"".equals(emp.getEmpName()))
				e.setEmpName(emp.getEmpName());
			if(Objects.nonNull(emp.getEmpSalary()))
				e.setEmpSalary(emp.getEmpSalary());
			if(Objects.nonNull(emp.getEmpEmailId()) || !"".equals(emp.getEmpEmailId()))
				e.setEmpEmailId(emp.getEmpEmailId());
			if(Objects.nonNull(emp.getEmpCellNo()) || !"".equals(emp.getEmpCellNo()))
				e.setEmpCellNo(emp.getEmpCellNo());
			if(Objects.nonNull(emp.getEmpAddress()) || !"".equals(emp.getEmpAddress()))
				e.setEmpAddress(emp.getEmpAddress());
			if(Objects.nonNull(emp.getEmpExperience()))
				e.setEmpExperience(emp.getEmpExperience());
			if(Objects.nonNull(emp.getUsername()) || !"".equals(emp.getUsername()))
				e.setUsername(emp.getUsername());
			if(Objects.nonNull(emp.getPassword()) || !"".equals(emp.getPassword()))
				e.setPassword(passwordEncoder.encode(emp.getPassword()));
			return repo.save(e);
		}
	}

	public Employee updateSelfByDirector(int empId, Employee emp) throws EmployeeNotFoundException {
		if(empId!=999)
			throw new EmployeeNotFoundException("Employee not found");
		else {
			Employee e = repo.findById(empId).get();
			if(Objects.nonNull(emp.getEmpName()) || !"".equals(emp.getEmpName()))
				e.setEmpName(emp.getEmpName());
			if(Objects.nonNull(emp.getEmpSalary()))
				e.setEmpSalary(emp.getEmpSalary());
			if(Objects.nonNull(emp.getEmpEmailId()) || !"".equals(emp.getEmpEmailId()))
				e.setEmpEmailId(emp.getEmpEmailId());
			if(Objects.nonNull(emp.getEmpCellNo()) || !"".equals(emp.getEmpCellNo()))
				e.setEmpCellNo(emp.getEmpCellNo());
			if(Objects.nonNull(emp.getEmpAddress()) || !"".equals(emp.getEmpAddress()))
				e.setEmpAddress(emp.getEmpAddress());
			if(Objects.nonNull(emp.getEmpExperience()))
				e.setEmpExperience(emp.getEmpExperience());
			if(Objects.nonNull(emp.getUsername()) || !"".equals(emp.getUsername()))
				e.setUsername(emp.getUsername());
			if(Objects.nonNull(emp.getPassword()) || !"".equals(emp.getPassword()))
				e.setPassword(passwordEncoder.encode(emp.getPassword()));
			return repo.save(e);
		}
	}

	public Employee getOne(int empId) throws EmployeeNotFoundException {
		if(!repo.existsById(empId))
			throw new EmployeeNotFoundException("Employee Not Found!!");
		else
			return repo.findById(empId).get();
	}

	public Employee getEmployee(int empId) throws EmployeeNotFoundException {
		if(!repo.existsById(empId) || empId==999)
			throw new EmployeeNotFoundException("Employee not Found!!");
		else
			return repo.findById(empId).get();
	}

	public List<Employee> getByJoiningDate(LocalDate date) {
		// TODO Auto-generated method stub
		return repo.findByJoiningDate(date);
	}

	public List<Employee> getByDateBetween(LocalDate from, LocalDate to) {
		// TODO Auto-generated method stub
		return repo.findByJoiningDateBetween(from, to);
	}

}
