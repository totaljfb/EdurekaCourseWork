package module3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MySearchHelper {
	public Integer[] search(Double[] list, Double number) {	
		List<Integer> al = new ArrayList<>();
		for(int i = 0; i< list.length; i++) {
				if(list[i].equals(number)) {
					al.add(i);
				}
			}
		Integer[] array_int = new Integer[al.size()];
		array_int = al.toArray(array_int);
		return array_int;
	}
//simple test
public static void main(String[] args) {
	MySearchHelper msh = new MySearchHelper();
	Double[] list = new Double[] {1.2,2.2,3.3,4.4,8.0,0.2,9.0,11.777,1.2,3.3,1.2,1.2,2.2,2.2};
	Integer[] al = msh.search(list, 1.2);
	System.out.println("The indexes of the input number occurs in the arry are: ");
	System.out.println(Arrays.toString(al));
}
}