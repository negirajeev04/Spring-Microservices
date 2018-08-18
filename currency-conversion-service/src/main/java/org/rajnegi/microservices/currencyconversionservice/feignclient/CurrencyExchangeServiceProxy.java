package org.rajnegi.microservices.currencyconversionservice.feignclient;

import org.rajnegi.microservices.currencyconversionservice.beans.CurrencyConverterBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchange-service", url="localhost:8000")
//@FeignClient(name="currency-exchange-service")
@FeignClient(name="netflix-zuul-api-gateway-server")// Go through API gateway
@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

	/*### To Route through the API gateway
	# http://localhost:8765/{application-name}/{uri}
	# ex - for currency-exchange-service
	# application-name - currency-exchange-service and uri - currency-exchange/from/eur/to/inr
	# http://localhost:8765/currency-exchange-service/currency-exchange/from/eur/to/inr
	# http://localhost:8765 is resolved through the eureka server
	# If we want the request to currency-conversion-service to also go through the API gateway then the url to use - 
	# http://localhost:8765/currency-conversion-service/currency-converter-feign/from/eur/to/inr/quantity/100
	# host and port_resolvedthrougheureka-/--application-name--/--uri--------------------------------------------------------------
	*/
	//@GetMapping(value = "/currency-exchange-service/from/{from}/to/{to}")
	@GetMapping(value = "currency-exchange-service/currency-exchange/from/{from}/to/{to}")// Go through API gateway
	public CurrencyConverterBean retrieveConversionMultiple(@PathVariable("from") String from, @PathVariable("to") String to);
	
}
