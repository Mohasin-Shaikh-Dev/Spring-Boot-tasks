package com.example.www.service.impl;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.www.entity.Employee;
import com.example.www.repository.EmployeeRepository;

import jakarta.validation.Valid;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

	@Override
	public String deleteEmployeeById(int id) {
		employeeRepository.findById(id);
		return "deleted Successfully";
		
	}

	@Override
	public ResponseEntity<Employee> addEMployee(@Valid Employee employee) {
		Employee e2= employeeRepository.save(employee);
		 return ResponseEntity.ok(e2);
		 
	}


	
}
