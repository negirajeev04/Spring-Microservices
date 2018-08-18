package org.rajnegi.microservices.limitsservice.controllers;

import org.rajnegi.microservices.limitsservice.bean.LimitsConfiguration;
import org.rajnegi.microservices.limitsservice.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class LimitsConfigurationController {
	
	@Autowired
	private Configuration configuration;
	
	@GetMapping(value="/limits")
	public LimitsConfiguration retrieveLimitsFromConfiguration() {
		return new LimitsConfiguration(configuration.getMaximum(),configuration.getMinimum());
	}
	
	@GetMapping(value="/fault-tolerance-example")
	@HystrixCommand(fallbackMethod="fallbackConfiguration")
	public LimitsConfiguration retrieveConfiguration() {
		throw new RuntimeException();
	}
	
	public LimitsConfiguration fallbackConfiguration() {
		return new LimitsConfiguration(9999,9);
	}
}
