package org.rajnegi.microservices.limitsservice.bean;

public class LimitsConfiguration {
	private int max;
	private int min;
	
	public LimitsConfiguration(int max, int min) {
		super();
		this.max = max;
		this.min = min;
	}

	public LimitsConfiguration() {
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}
	
	
}
