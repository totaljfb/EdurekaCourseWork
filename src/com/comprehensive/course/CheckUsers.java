package com.comprehensive.course;
import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;
//software environment: jdk-1.8, MySQL Server 5.7, Connector/J 8.0.11
public class CheckUsers {

	public static void main(String[] args) {
		try {
			//connect to MySQL
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/demo?verifyServerCertificate=false&useSSL=true";
			String username = "root";
			String password = "root";
			Connection con = DriverManager.getConnection(url,username,password);
			Statement st = con.createStatement();
			String s = "select * from user_credential";
			ResultSet rs = st.executeQuery(s);
			//use dictionary to store all the users and passwords
			HashMap<String, String> hm = new HashMap<String, String>();
			while(rs.next()) {
				hm.put(rs.getString("username"), rs.getString("password"));
			}
			st.close();
			con.close();
			//valid user input with user name and password
			System.out.println("Enter the user name and password to verify:");
			Scanner sc = new Scanner(System.in);
			String user = sc.nextLine();
			String psw = sc.nextLine();
			sc.close();
			if(hm.containsKey(user)&&psw.equals(hm.get(user))){
				System.out.println("valid user name and password!");
			}
			else {
				System.out.println("invalid user name and password!");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
