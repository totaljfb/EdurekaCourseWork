package com.edureka.a2;

public class Square extends Quadrilateral {
	private int length;
	//Constructor for super class
	Square(int base, int height) {
		super(base, height);
		//to initialize the square object, we assume base = height
		this.length = base;
	}

	@Override
	public double area() {
		return (double)(length*length);
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
