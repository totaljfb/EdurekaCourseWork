package com.edureka.advanced.java.a3.q4;

public class Event {
	//the expected event
	private double expected_number;
	private int expected_digit;
	//getters, will use constructor instead of setters
	public int getExpected_digit() {
		return expected_digit;
	}
	public double getExpected_number() {
		return expected_number;
	}
	//constructor, to get the expected number when event happens
	Event(double expected_number, int expected_digit){
		this.expected_digit = expected_digit;
		this.expected_number = expected_number;
	}
	
}
