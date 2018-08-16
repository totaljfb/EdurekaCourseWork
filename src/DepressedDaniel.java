import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DepressedDaniel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int op;
		ArrayList<Integer> arl = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		boolean quit = false;
		do {
			System.out.println("Please select a choice:");
			System.out.println("");
			System.out.println("[1]. Show the current ArrayList");
			System.out.println("[2]. Add an element to the ArrayList");
			System.out.println("[3]. Remove an element from the list");
			System.out.println("[4]. Show duplicate elements in ArrayList");
			System.out.println("[5]. Quit Program");
			op = sc.nextInt();
			switch(op) {
			case 1: System.out.println("Current ArrayList:");
					System.out.println(arl.toString());
					break;
			case 2: System.out.println("Please enter an integer:");
					op = sc.nextInt();
					arl.add(op);
					break;
			case 3: System.out.println("Current ArrayList indices and elements:");
					for(int i = 0; i<arl.size(); i++) {
					System.out.println("Index[" + i + "]: " + arl.get(i) );
			}
					System.out.println("Please enter the index of the element which you want to remove:");
					try {
						op = sc.nextInt();
						arl.remove(op);
						System.out.println("Element with index of " + op +" has been removed from the ArrayList");
					} 
					catch(Exception e) {
						e.printStackTrace();
					}					
					break;
			case 4: Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
					for(int i:arl) {
						if(counts.containsKey(i)){
							counts.put(i, counts.get(i)+1);
						}
						else {
							counts.put(i, 1);
						}
					}
					ArrayList<Integer> duparl = new ArrayList<>(); 
					for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
						if(entry.getValue() > 1) {
							duparl.add(entry.getKey());
						}    
					}
					if(duparl.size()>0) {
						System.out.println("Here are the duplicate elements in the ArrayList:");
						System.out.println(duparl.toString());
					}
					else {
						System.out.println("There is no duplicate element in the ArrayList");
					}
					break;
			case 5: System.out.println("Quit");
					quit = true;
					break;
			}
		} while(quit != true); 
		sc.close();
	}

}
