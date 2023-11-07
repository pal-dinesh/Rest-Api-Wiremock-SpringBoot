package com.myProject.springboot.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myProject.springboot.service.App_Service;
import com.myProject.springboot.service.Users;

@RestController
public class AppController {

	
	private final App_Service app_service;
	
	
	@Autowired
	public AppController(App_Service app_service)
	{
		this.app_service = app_service;
	}
	
	@GetMapping("/")
	public ResponseEntity<Users> getUser()
	{
		return new ResponseEntity<> (app_service.getApiUser() , HttpStatus.OK);
	
	}
	
}
