package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	Employee findByUsername(String username); 
	List<Employee> findByManagerId(int managerId);
	List<Employee> findByJoiningDate(LocalDate date);
	List<Employee> findByJoiningDateBetween(LocalDate from, LocalDate to);
}
