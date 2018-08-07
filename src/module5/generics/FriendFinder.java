package module5.generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FriendFinder {
	//Q3.b, method for search friend when T = Integer and S = String 
	public static int findFriend1(List<FriendshipCriteria<String,Integer>> friends, FriendshipCriteria<String,Integer> f){ 
		return Collections.binarySearch(friends, f); 
		}
	//Q3.c, method for search friend when T = String and S = String 
	public static int findFriend2(List<FriendshipCriteria<String,String>> friends, FriendshipCriteria<String,String> f){ 
		return Collections.binarySearch(friends, f); 
	}	
	
	public static void main(String[] args){
		// Creating the friendship criteria list1, String and Integer
		List<FriendshipCriteria<String,Integer>> list1 = new ArrayList<FriendshipCriteria<String,Integer>>();
		list1.add(new FriendshipCriteria<String,Integer>(new String("Jason"), new Integer(30)));
		list1.add(new FriendshipCriteria<String,Integer>(new String("Alex"), new Integer(40)));
		list1.add(new FriendshipCriteria<String,Integer>(new String("Cheng"), new Integer(22)));
		
		// Creating the friendship criteria list2, String and String
		List<FriendshipCriteria<String,String>> list2 = new ArrayList<FriendshipCriteria<String,String>>();
		list2.add(new FriendshipCriteria<String,String>(new String("Jason"), new String("D.C.")));
		list2.add(new FriendshipCriteria<String,String>(new String("Alex"), new String("New York")));
		list2.add(new FriendshipCriteria<String,String>(new String("Cheng"), new String("Tokyo")));
		
		
		// Search for my friend1
		FriendshipCriteria<String, Integer> f1 = new FriendshipCriteria<String, Integer>("Alex", 40);
		int serarch_result1 = FriendFinder.findFriend1(list1, f1);
		// Search for my friend2
		FriendshipCriteria<String, String> f2 = new FriendshipCriteria<String, String>("Tim", "Tokyo");
		int serarch_result2 = FriendFinder.findFriend2(list2, f2);
		
		//show the result
		if (serarch_result1 < 0) {
			System.out.println(f1.getT() + "," + f1.getS() + " not found...");
		}
		else {
			System.out.println(f1.getT() + "," + f1.getS() + " found!");
		}
		
		if (serarch_result2 < 0) {
			System.out.println(f2.getT() + "," + f2.getS() + " not found");
		}
		else {
			System.out.println(f2.getT() + "," + f2.getS() + " found");
		}
		}

}
