package com.edureka.a2;

public class FindIntegers {
	public void find_int(int[] int_array, int input) {
		for(int i=0; i<int_array.length;i++) {
			if(i == input) {
				printfound(input,i);
			}
		}
	}
	public void printfound(int in, int index) {
		System.out.println("Integer " + in + " found in array, index is " + index);
	}
	//Simple test for the class
	public static void main(String[] args) {
		int[] int_array = new int[5];
		for(int i = 0; i<5; i++) {
			int_array[i] = i;
 		}
		FindIntegers fi = new FindIntegers();
		fi.find_int(int_array, 4);
	}

}
