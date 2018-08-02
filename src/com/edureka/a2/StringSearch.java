package com.edureka.a2;

public class StringSearch extends AbstractSearch {
	@Override
	public boolean search(Object[] obj_list, Object obj) {
		//setup a boolean variable for returned value
		boolean strfound = false;
		//loop through the object list to search the given obj
		for(Object ob:obj_list) {
			//if found, set return value to be true
			if(ob.equals(obj)) {
				strfound = true;
			}	
		}
		return strfound;
	}
	//simple test for the class
	public static void main(String[] args) {
		Object[] il = new Object[5];
		for(int i = 0; i<5; i++) {
			il[i] = String.valueOf(i);
		}
		StringSearch ss = new StringSearch();
		System.out.println(ss.search(il, "3"));
	}
}
