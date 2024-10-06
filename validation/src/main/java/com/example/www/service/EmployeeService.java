package com.example.www.service;

import java.util.Optional;
import com.example.www.entity.Employee;

public interface EmployeeService {
	Optional<Employee> getEmployeeById(int id);
	String deleteEmployeeById(int id);
}
