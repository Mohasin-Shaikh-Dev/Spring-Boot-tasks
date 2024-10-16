package com.example.www.service;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.www.entity.Employee;
import com.example.www.repository.EmployeeRepository;
import com.example.www.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
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


	
}
