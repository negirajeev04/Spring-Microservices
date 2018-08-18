package org.rajnegi.microservices.currencyconversionservice.controllers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.rajnegi.microservices.currencyconversionservice.beans.CurrencyConverterBean;
import org.rajnegi.microservices.currencyconversionservice.feignclient.CurrencyExchangeServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyConversionController.class);
	
	@Autowired
	private CurrencyExchangeServiceProxy exchFeignClient;
	
	@GetMapping(value = "/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConverterBean retrieveCurrencyConversion(@PathVariable String from, @PathVariable String to, 
			@PathVariable BigDecimal quantity) {
		
		
		Map<String,String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		ResponseEntity<CurrencyConverterBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
				CurrencyConverterBean.class, 
				uriVariables );
		
		CurrencyConverterBean response = responseEntity.getBody();
		
		
		CurrencyConverterBean currencyConverterBean = new CurrencyConverterBean(
				response.getId(), 
				from, to, 
				response.getConversionMultiple(), 
				quantity, 
				quantity.multiply(response.getConversionMultiple()));
		
		currencyConverterBean.setPort(response.getPort());
		
		return currencyConverterBean;
	}
	
	@GetMapping(value = "/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConverterBean retrieveCurrencyConversionFeign(@PathVariable String from, @PathVariable String to, 
			@PathVariable BigDecimal quantity) {
		
		
		CurrencyConverterBean response = exchFeignClient.retrieveConversionMultiple(from, to);
		
		LOGGER.info("Inside currency-converter, multiple {}", response.getConversionMultiple());
		CurrencyConverterBean currencyConverterBean = new CurrencyConverterBean(
				response.getId(), 
				from, to, 
				response.getConversionMultiple(), 
				quantity, 
				quantity.multiply(response.getConversionMultiple()));
		
		currencyConverterBean.setPort(response.getPort());
		
		return currencyConverterBean;
	}
	
}
