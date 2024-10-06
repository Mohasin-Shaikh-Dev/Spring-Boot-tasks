package com.example.www.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.www.entity.Employee;
import com.example.www.exception.ResourceNotFound;
import com.example.www.service.impl.EmployeeService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/add")
	private ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee)
	{
		return employeeService.addEMployee(employee);
	}
	
	@GetMapping("/fetch/{id}")
	public Employee getEmployeeById(@PathVariable int id)
	{
		return employeeService.getEmployeeById(id).orElseThrow(()-> new ResourceNotFound("employee not found"));
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteEmployeeById(@PathVariable int id)
	{
	    employeeService.getEmployeeById(id).orElseThrow(()-> new ResourceNotFound("jab employye hai hi nahi to delete kaise hoga (ha ha ha...)"+ id));
		return employeeService.deleteEmployeeById(id);
	}
			
}
