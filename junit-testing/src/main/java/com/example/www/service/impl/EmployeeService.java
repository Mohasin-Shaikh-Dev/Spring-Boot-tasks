package com.example.www.service.impl;

import java.util.List;

import com.example.www.entity.Employee;

public interface EmployeeService {

	Employee addEmployee(Employee employee);

	List<Employee> getEmployee();

	Employee getEmployeeById(int id);

	String deleteEmployeeById(int id);

	Employee updateEmployee(int id, Employee employee);

	Employee getEmployeeByName(String name);

}
