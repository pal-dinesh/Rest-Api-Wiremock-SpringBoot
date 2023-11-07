package com.myProject.springboot.service;

import org.apache.catalina.User;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class App_Service {

	//private final RestTemplate restTemplate;
	
	@Autowired
	private RestTemplate restTemplate;
	
//	@Autowired
//	public App_Service(RestTemplate restTemplate)
//	{
//		this.restTemplate = restTemplate;
//	}
	
	public Users getApiUser()
	{
		return restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos/1", Users.class);
	}
}
