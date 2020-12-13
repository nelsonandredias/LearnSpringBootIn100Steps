package com.polarising.spring.boot.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


//the best way to reference a bulk of property values at the same time
// for that, create all configurations in a single bean
//besides, this strategy is type safe -> we will get an error if some value does not have the correct data type

@Component
@ConfigurationProperties("basic")
public class BasicConfiguration {
	
	private boolean value;
	private String message;
	private int number;
	
	
	public boolean isValue() {
		return value;
	}
	public void setValue(boolean value) {
		this.value = value;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
	

}
