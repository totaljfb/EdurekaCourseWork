package module5.generics;

import java.util.Arrays;

public class GenericUtils {
	
	public static <T> T[] swap(T[] list, int firstPos, int secondPos) {
		
		if((firstPos < 0 || firstPos > list.length)) {
			throw new IndexOutOfBoundsException("Index " + firstPos + " is out of bounds!");
		}
		else if((secondPos < 0 || secondPos > list.length)) {
			throw new IndexOutOfBoundsException("Index " + secondPos + " is out of bounds!");
		}
		else {
			T a = list[firstPos];
			list[firstPos] = list[secondPos];
			list[secondPos] = a;
			return list;
		}
		
	}
	
	//simple test, generics does not support primitive types
	public static void main(String[] args) {
		
		String[] a = {"a","b","c"};
		System.out.println(Arrays.toString(a));
		swap(a,1,0);
		System.out.println(Arrays.toString(a));
		
		Integer[] b = {1,2,3};
		System.out.println(Arrays.toString(b));
		swap(b,1,0);
		System.out.println(Arrays.toString(b));
		
		Float[] c = {1.1f,2.2f,3.3f};
		System.out.println(Arrays.toString(c));
		swap(c,1,0);
		System.out.println(Arrays.toString(c));
		
		//exception test
		swap(a,1,5);
		System.out.println(Arrays.toString(a));
	}
	

}
