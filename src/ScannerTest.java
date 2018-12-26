import java.util.Scanner;

public class ScannerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		System.out.println(s);
		sc.nextLine();
		s = sc.nextLine();
		System.out.println(s);
		sc.close();
		
	}

}
