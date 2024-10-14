package com.example.www.jwt;

import lombok.Data;

@Data
public class LoginResponse {
    private String jwtToken;
    private String username;
    private String roles;
    public LoginResponse( String username, String roles,String jwtToken) {
		super();
		this.username = username;
		this.roles = roles;
		this.jwtToken = jwtToken;
	}
	

   

}