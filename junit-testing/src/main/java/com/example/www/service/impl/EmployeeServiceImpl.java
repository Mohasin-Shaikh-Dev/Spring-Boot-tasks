package com.example.www.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.www.entity.Employee;
import com.example.www.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {


	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getEmployee() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(int id) {
		return employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@Override
	public String deleteEmployeeById(int id) {
		employeeRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
		employeeRepository.deleteById(id);
		return "delete Successfully";
	}

	@Override
	public Employee updateEmployee(int id, Employee employee) {
		Employee employee1=employeeRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
		employee1.setEmp_id(id);
		employee1.setEmpName(employee.getEmpName());
		employee1.setEmp_add(employee.getEmp_add());
		employeeRepository.save(employee1);
		return employee1;
	}

	@Override
	public Employee getEmployeeByName(String name) {
		return employeeRepository.findByEmpName(name);
	}



}
