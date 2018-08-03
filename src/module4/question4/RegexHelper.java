package module4.question4;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RegexHelper {

	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)){
			System.out.println("Please enter the regular expression: ");
			String regex = sc.nextLine();
			System.out.println("Please enter the string you want to parse: ");
			String input = sc.nextLine();
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(input);
			if(m.find()) {
				System.out.println("Matched string is: ");
				for(int i = 0; i <= m.groupCount(); i++) {
					System.out.print(m.group(i));
				}	
			}else {System.out.println("No Match found");}
		}catch (PatternSyntaxException e) {
			e.printStackTrace();
		}

	}

}

