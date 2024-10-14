package com.example.www.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {
	
  @GetMapping("/")
  public String getData()
  {
	  return "get called";
  }
}
