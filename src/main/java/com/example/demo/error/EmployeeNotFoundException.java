package com.example.demo.error;

public class EmployeeNotFoundException extends Exception {
	public EmployeeNotFoundException(String s) {
		super(s);
	}
}
