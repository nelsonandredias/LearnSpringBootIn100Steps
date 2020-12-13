package com.polarising.spring.boot.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polarising.spring.boot.configurations.BasicConfiguration;
import com.polarising.spring.boot.services.WelcomeService;

@RestController
public class WelcomeController {

	@Autowired
	private WelcomeService welcomeService;
	
	@Autowired
	private BasicConfiguration basicConfiguration;
	
	@RequestMapping("/welcome")
	public String welcomeMessage() {
		return welcomeService.retrieveWelcomeMessage();
	}
	
	
	@RequestMapping("/dynamic-configuration")
	public Map dynamicConfiguration() {
		
		Map map = new HashMap();
		
		map.put("number", basicConfiguration.getNumber());
		map.put("message", basicConfiguration.getMessage());
		map.put("value", basicConfiguration.isValue());
		
		return map;
	}
	
}
