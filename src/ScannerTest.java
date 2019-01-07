import java.util.Scanner;

public class ScannerTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			int menu_selection = 0;
			do {
				System.out.println("");
				System.out.println("Please select one option from the following list: ");
				System.out.println("[1] Query the database and get the attendance for all employees for any day or month");
				System.out.println("[2] Query the database and get the attendance for the month or a day for a particular employee");
				System.out.println("[3] Query the database to get the list of employees along with the number of times they had less than 8 hours");
				System.out.println("[4] Save the query result as a .CSV file");
				System.out.println("[5] Upload the .CSV file to a server");
				System.out.println("[6] Quit the program");
				String input = sc.next();
				menu_selection = Integer.parseInt(input);
			}while(menu_selection!=6 );
			System.out.println("Program ended");
			sc.close();
		}catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
	}
	}

}
