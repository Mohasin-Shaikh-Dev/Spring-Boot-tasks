package com.example.www.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.www.entity.Employee;
import com.example.www.service.impl.EmployeeService;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/fetch")
	public List<Employee> getEmployee()
	{
		return employeeService.getEmployee();
	}
	
	@PostMapping("/add-emp")
	public Employee addEmployee(@RequestBody Employee employee)
	{
		return employeeService.addEmployee(employee);
	}
	
	@GetMapping("/fetch/{id}")
	public Employee getEmployeeById(@PathVariable int id)
	{
		return employeeService.getEmployeeById(id);
	}
	
	@GetMapping("/fetchs/{name}")
	public Employee getEmployeeByName(@PathVariable String name)
	{
		return employeeService.getEmployeeByName(name);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteEmployeeById(@PathVariable int id)
	{
		return employeeService.deleteEmployeeById(id);
	}
	
	@PostMapping("/update/{id}")
	public Employee updateEmployee(@PathVariable int id,@RequestBody Employee employee)
	{
		return employeeService.updateEmployee(id,employee);
	}
		
}
