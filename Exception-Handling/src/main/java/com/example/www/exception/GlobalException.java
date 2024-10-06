package com.example.www.exception;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<?> exceptionHandler(ResourceNotFound e)
	{
		ErrorResponse e2=new ErrorResponse(e.getMessage(),LocalDateTime.now(),"nahi milra");
		return new ResponseEntity<>(e2,HttpStatus.NOT_FOUND);
	}
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> exceptionHandler(MethodArgumentNotValidException ex)
	{
		Map<String, String> map=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldname=((FieldError)error).getField();
			String fieldMsg=error.getDefaultMessage();
			map.put(fieldname, fieldMsg);
		});
		
		return new ResponseEntity<Map<String,String>>(map,HttpStatus.NOT_FOUND);
	}
	
	
}
