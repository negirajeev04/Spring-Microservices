package org.rajnegi.microservices.netflixzuulapigatewayserver;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulLoggingFilter extends ZuulFilter{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ZuulLoggingFilter.class);

	@Override
	public Object run() throws ZuulException {
		HttpServletRequest request = 
				RequestContext.getCurrentContext().getRequest();
		LOGGER.info("Request uri is {}",request.getRequestURI());
		return null;
	}

	@Override
	public boolean shouldFilter() {
		//Execute the business logic to check the request and decide if you want to execute this filter
		return true;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public String filterType() {
		//possible values
		//pre
		//post
		//error
		return "pre";
	}

}
