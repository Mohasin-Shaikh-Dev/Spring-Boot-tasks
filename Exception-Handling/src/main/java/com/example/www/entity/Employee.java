package com.example.www.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int emp_id;
	
	@NotEmpty
	@Size(min = 3,message = "user name conatains minimun 3 character")
	@Column(name="Name")
	private String emp_name;
	
	@Column(name = "Address")
	@NotEmpty(message = "Addess not be empty")
	private String emp_add;

	@NotEmpty 
	@Email(message = "email not valid")
	@Column(name="Email")
	private String emp_email;
}
