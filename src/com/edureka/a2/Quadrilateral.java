package com.edureka.a2;

public abstract class Quadrilateral {
	private int base;
	private int height;
	//Default Constructor
	Quadrilateral(){};
	//Parameterized Constructor
	Quadrilateral(int base, int height){
		this.base = base;
		this.height = height;
	}
	public abstract double area();

	public int getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
