package org.rajnegi.microservices.currencyexchangeservice.controllers;

import java.util.Date;

import org.rajnegi.microservices.currencyexchangeservice.ExchangeValueRepository;
import org.rajnegi.microservices.currencyexchangeservice.bean.ExchangeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyExchangeController.class);

	@Autowired
	private Environment environment;
	@Autowired
	private ExchangeValueRepository exchangeRepo;
	
	@GetMapping(value = "/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveConversionMultiple(@PathVariable String from, @PathVariable String to) {

		ExchangeValue exchangeValue = exchangeRepo.findByFromAndTo(from.toUpperCase(), to.toUpperCase());
		if(exchangeValue == null)
			throw new ResourceNotFoundException("Exchange Value Not Found");
		
		LOGGER.info("Inside currency-exchange, multiple {}", exchangeValue.getConversionMultiple());
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		
		return exchangeValue;
	}
	
	@ExceptionHandler(value = ResourceNotFoundException.class)
	public final ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException rex) {
		
		ExceptionResponse response = new ExceptionResponse(new Date(), "Requested Resource Not Found", rex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		
	}
	
}
