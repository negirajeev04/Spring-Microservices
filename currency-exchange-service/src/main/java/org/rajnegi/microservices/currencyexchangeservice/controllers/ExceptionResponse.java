package org.rajnegi.microservices.currencyexchangeservice.controllers;

import java.util.Date;

public class ExceptionResponse {

	private Date timestamp;
	private String message;
	private String description;
	
	public ExceptionResponse(Date timestamp, String description, String message) {
		this.timestamp = timestamp;
		this.description = description;
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
