package com.example.www.service.impl;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.www.entity.Employee;

import jakarta.validation.Valid;

public interface EmployeeService {
	Optional<Employee> getEmployeeById(int id);
	String deleteEmployeeById(int id);
	ResponseEntity<Employee> addEMployee(@Valid Employee employee);
}
