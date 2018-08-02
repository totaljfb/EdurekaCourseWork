package com.edureka.a2;

public class Rectangle extends Quadrilateral {
	private int width;
	private int length;
	Rectangle(int base, int height) {
		super(base, height);
		//here we assume base is the length of the rectangle
		//height is the width of the rectangle
		this.width = base;
		this.length = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	@Override
	public double area() {
		return (double)(width*length);
	}

}
