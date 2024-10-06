package com.example.www.exception;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> exceptionHandler(Exception e)
	{
		ErrorResponse e2=new ErrorResponse(e.getMessage(),LocalDateTime.now(),"nahi milra");
		return new ResponseEntity<>(e2,HttpStatus.NOT_FOUND);
	}
}
