package com.example.www.serviceTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import com.example.www.entity.Employee;
import com.example.www.repository.EmployeeRepository;
import com.example.www.service.impl.EmployeeServiceImpl;

@SpringBootTest
public class EmployeeServiceTests {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Mock
	private EmployeeRepository employeeRepository2;
	
   @InjectMocks
   private EmployeeServiceImpl emServiceImpl;
	
	private static Employee employee;
	
    @BeforeAll
    public static void setUpBeforeAll() {
        // Runs once before all tests
        // Set up shared resources, like an Employee object
        employee = new Employee(1, "pavan", "goa");
    }

    @BeforeEach
    public void setUpBeforeEach() {
        // Runs before each test case
        // Initialize the mocks
        MockitoAnnotations.openMocks(this);
    }


	@ParameterizedTest
	@ValueSource(strings = {
			"shaikh",
			"sohail",
			"tejas"
	})
	public void testgetEmployeeByName(String name) {
		Assertions.assertNotNull(employeeRepository.findByEmpName(name),"Employee Not found"); 
	}
	
	
	@Test	
	public void testgetEmployee()
	{
		Assertions.assertTrue(!employeeRepository.findAll().isEmpty());
	}
	
	
	@Test
	public void testGetEmployeeByName_With_Mockito() {
		String username=employee.getEmpName();
	    // Mock the behavior of the repository
	    when(employeeRepository2.findByEmpName(username))
	         .thenReturn(new Employee(1, username, "solapur"));
	    
	    // Call the repository method 
	    Employee employee = employeeRepository2.findByEmpName("pavan");

	    // Perform assertions
	    Assertions.assertNotNull(employee, "Employee should not be null");
	    Assertions.assertEquals(username, employee.getEmpName(), "Employee name should match");
	}
	
	
	// Test for updating an employee
    @Test
    public void testUpdateEmployee_Success() {
        when(employeeRepository2.findById(1)).thenReturn(Optional.of(employee));

        Employee updatedEmployee = new Employee(1, "pavan updated", "pune");
        when(employeeRepository2.save(any(Employee.class))).thenReturn((Employee) updatedEmployee);

        Employee result = emServiceImpl.updateEmployee(1, updatedEmployee);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("pavan updated", result.getEmpName());
        Assertions.assertEquals("pune", result.getEmp_add());
        verify(employeeRepository2, times(1)).save(any(Employee.class));
    }

    // Test for updating an employee (Not Found)
    @Test
    public void testUpdateEmployee_NotFound() {
        when(employeeRepository2.findById(1)).thenReturn(Optional.empty());

        Employee updatedEmployee = new Employee(1, "pavan updated", "pune");

        Assertions.assertThrows(ResponseStatusException.class, () -> {
            emServiceImpl.updateEmployee(1, updatedEmployee);
        });

        verify(employeeRepository2, times(1)).findById(1);
    }

    // Test for deleting an employee by ID
    @Test
    public void testDeleteEmployeeById_Success() {
        when(employeeRepository2.findById(1)).thenReturn(Optional.of(employee));

        String result = emServiceImpl.deleteEmployeeById(1);

        Assertions.assertEquals("delete Successfully", result);
        verify(employeeRepository2, times(1)).deleteById(1);
    }

    // Test for deleting an employee by ID (Not Found)
    @Test
    public void testDeleteEmployeeById_NotFound() {
        when(employeeRepository2.findById(1)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResponseStatusException.class, () -> {
            emServiceImpl.deleteEmployeeById(1);
        });

        verify(employeeRepository2, times(1)).findById(1);
    }
	
	
	
	
}
