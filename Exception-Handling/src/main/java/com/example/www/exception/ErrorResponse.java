package com.example.www.exception;
import java.time.LocalDateTime;
public class ErrorResponse {
	
	private String message;
	private LocalDateTime time;
	private String errorCode;
	
	public ErrorResponse() {
		super();
	}
	public ErrorResponse(String message, LocalDateTime time, String errorCode) {
		super();
		this.message = message;
		this.time = time;
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	
}
