package com.example.www;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController    // to handle the HTTP method
public class myController {
	
	@GetMapping("/get")
	public String getRequest()
	{
		return "get HTTP method Called";
	}
	
	@PostMapping("/post")
	public String postRequest()
	{
		return "post HTTP method Called";
	}
	
	@PutMapping("/put")
	public String putRequest()
	{
		return "put HTTP method Called";
	}
	
	@DeleteMapping("/delete")
	public String DeleteRequest()
	{
		return "Delete HTTP method Called";
	}	
}
