package com.edureka.a2;

public class Parallelogram extends Quadrilateral {
	private int side1;
	private int side2;
	private double included_angle;//in degree
	Parallelogram(int base, int height) {
		super(base, height);
		this.setSide1(base);
		this.setSide2(height);
	}

	@Override
	public double area() {
		double included_angel_radian =  Math.toRadians(included_angle);
		double height = side2 * Math.cos(included_angel_radian);
		return (double)(side1*height);
	}

	public int getSide1() {
		return side1;
	}

	public void setSide1(int side1) {
		this.side1 = side1;
	}

	public int getSide2() {
		return side2;
	}

	public void setSide2(int side2) {
		this.side2 = side2;
	}

	public double getIncluded_angle() {
		return included_angle;
	}

	public void setIncluded_angle(double included_angle) {
		this.included_angle = included_angle;
	}

}
