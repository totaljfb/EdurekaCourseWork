package module5.generics;

import java.util.List;
import java.util.Vector;

public class ArithmeticOperations {
	
	public static <T extends Number> Number add(T t1, T t2) {
		return (t1.doubleValue() + t2.doubleValue());
	}
	
	public static <T extends Number> Number add(List<? extends Number> list) {
		double d = 0;
		for(int i = 0; i < list.size(); i++) {
			d += list.get(i).doubleValue();
		}
		return d;
	}
	
	public static void dumpList(List<?> list) {
		System.out.println("List dump with unboudned wildcard:");
		for(int i=0; i<list.size();i++) {
			System.out.println(list.get(i));
		}
	}
	
	//subtract method, define as t1 subtracts t2 
	public static <T extends Number> Number subtract(T t1, T t2) {
		return (t1.doubleValue()-t2.doubleValue());
	}
	
	//divide method, define as t1 being divided by t2
	public static <T extends Number> Number divide(T t1, T t2) {
		if(t2.doubleValue() != 0) {
			return t1.doubleValue()/t2.doubleValue();
		}else {
			System.out.println("Cannot divided by zero! 0 means you have got an exception");
			return 0;
		}
		
		
	}
	
	public static void main(String[] args) {
		//adding 2 integers
		Integer i1 = 34;
		Integer i2 = 43;
		System.out.println("Add with generic method: " + ArithmeticOperations.add(i1, i2));
		
		//adding 2 floats
		Float f1 = 12.56f;
		Float f2 = 3.6778f;
		System.out.println("Add with generic method: " + ArithmeticOperations.add(f1, f2));
		
		//adding 2 integers through a list
		Vector<Number> l = new Vector<Number>();
		l.add(34);
		l.add(43);
		System.out.println("Add with upper bounded wildcard: " + ArithmeticOperations.add(l));
		
		//dumping the list to the console
		ArithmeticOperations.dumpList(l);
		
		//subtract test
		System.out.println("Subtract with generic method: " + ArithmeticOperations.subtract(f1, f2));
		
		//divide test
		System.out.println("Divide with generic method: " + ArithmeticOperations.divide(f1, f2));
		System.out.println("Divide with generic method: " + ArithmeticOperations.divide(11.1, 0));
	}

}
