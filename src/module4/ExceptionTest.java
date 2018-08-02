package module4;

import java.util.ArrayList;
//simple test for prime number validation 
public class ExceptionTest {
	public static boolean validate_prime_int(ArrayList<Integer> al_int) {
		boolean result = true;
		//a simple way to test the prime numbers in a list
		for(int i:al_int) {
			if(i <= 2) {
				result = false;
				break;
			}
			else {
				if (i%2==0) {
					result = false;
					break;
				}
				for(int j = 3; j*j <= i; j+=2) {
					if(i%j == 0) {
						result = false;
						break;
					}
				}
			}
		}
		return result;
	}
	//create 3 array list with some prime and non-prime numbers
	public static void main(String[] args) {
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		list1.add(4);
		list1.add(6);
		list1.add(12);
		
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		list2.add(3);
		list2.add(5);
		list2.add(7);
		list2.add(11);
		
		ArrayList<Integer> list3 = new ArrayList<Integer>();
		list3.add(3);
		list3.add(4);
		list3.add(6);
		list3.add(13);
		
		PrimeIntegers pi1 = new PrimeIntegers(list1);
		PrimeIntegers pi2 = new PrimeIntegers(list2);
		PrimeIntegers pi3 = new PrimeIntegers(list3);
		
		//false means there is at least one non-prime number
		//true means they are all prime numbers
		System.out.println(validate_prime_int(pi1.getAl_int()));
		System.out.println(validate_prime_int(pi2.getAl_int()));
		System.out.println(validate_prime_int(pi3.getAl_int()));
		
		if(!validate_prime_int(pi1.getAl_int())){
			try {
				throw new NonPrimeIntegerException("There is at least one non "
						+ "prime number in the list");
			} catch (NonPrimeIntegerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
