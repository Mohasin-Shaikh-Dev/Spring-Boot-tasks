package com.example.www.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.www.entity.Users;
import com.example.www.jwt.JwtUtils;
import com.example.www.jwt.LoginRequest;
import com.example.www.jwt.LoginResponse;
import com.example.www.repository.UserRepository;

@RestController
public class GreetingsController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@GetMapping("/home")
	public String home()
	{
		return "Home Page";
	}
	
	@GetMapping("/admin")
	//@PreAuthorize("hasRole('ADMIN')")
	public String theAdmin()
	{
		return "hii... Admin";
	}
	
	//@PreAuthorize("hasRole('USER')")
	@GetMapping("/user")
	public String theUser()
	{
		return "hii... User";
	}
	
	@PostMapping("/add")
	public Users createUser(@RequestBody Users users)
	{
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		return userRepository.save(users);
	}
	
	 @PostMapping("/signin")
	    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
	        Authentication authentication;
	        try {
	            authentication = authenticationManager
	                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
	        } catch (AuthenticationException exception) {
	            Map<String, Object> map = new HashMap<>();
	            map.put("message", "Bad credentials");
	            map.put("status", false);
	            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
	        }

	   
	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

	        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);

	        String roles = userDetails.getAuthorities().toString();
	        LoginResponse response = new LoginResponse(userDetails.getUsername(), roles, jwtToken);

	        return ResponseEntity.ok(response);
	    }



}
