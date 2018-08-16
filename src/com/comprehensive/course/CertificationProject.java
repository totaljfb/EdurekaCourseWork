package com.comprehensive.course;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.cj.util.StringUtils;

public class CertificationProject {
	private final static String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	private final static String db_url = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false";	
	private final static String user = "root";
	private final static String psw = "336299";
	private static Statement statement;
	
	public static void CreateTable() {
		try {
		Class.forName(jdbc_driver);
		try {
			Connection conn = DriverManager.getConnection(db_url,user,psw);
			String create_table = "CREATE TABLE Movie " +
								  "(title VARCHAR(255) not NULL, " +
	                              " title_type VARCHAR(255), " + 
	                              " directors VARCHAR(255), " + 
	                              " imdb_rating DOUBLE, " + 
	                              " runtime DOUBLE, " +
	                              " year INT, " +
	                              " genres VARCHAR(255), " +
	                              " votes INT," +
	                              " top250 VARCHAR(255), " +
	                              " must_see_1001 VARCHAR(255), " +
	                              " url VARCHAR(255))"; 

			Statement stmt = conn.createStatement();
			stmt.executeUpdate(create_table);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	System.out.println("MySQL database table created.");	
	}
	
	public static void LoadMovie() {
		File file = new File("c:\\temp\\11_dataset_v1.0.txt");
		try {
			Class.forName(jdbc_driver);
			try {
				Connection conn = DriverManager.getConnection(db_url,user,psw);
				statement = conn.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Loading data to MySQL...");
		try {
			Scanner sc = new Scanner(file);
			while(sc.hasNextLine()) {
				String str = sc.nextLine();
				String[] Movie = str.split(";");
				String sql = "insert into test.movie value ";
				for(int i = 0; i < Movie.length; i++) {
					if(i == 0) {
						sql += "(\"" + Movie[i] + "\",";
					}
					else if(i == Movie.length - 1) {
						sql += "\"" + Movie[i] + "\")";
					} 
					else if(i == 3 || i == 4 || i == 5 || i ==7){
						if(StringUtils.isEmptyOrWhitespaceOnly(Movie[i])) {
							sql += "NULL,";
						}
						else sql += Movie[i] + ",";
					}
					else {
						sql += "\"" + Movie[i] + "\",";
					}
				}
				System.out.println(sql);
				try {
					statement.executeUpdate(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Loading data completed.");
	}
	
	public static PreparedStatement get_prepared_statement(String question) {
		PreparedStatement pstmt = null;
		try {
			Class.forName(jdbc_driver);
			try {
				Connection conn = DriverManager.getConnection(db_url,user,psw);
				pstmt = conn.prepareStatement(question);
			} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		return pstmt;
	} 
	
	public static void excute_query(PreparedStatement pstmt, String question_id) throws SQLException {
		ResultSet rs = pstmt.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		String single_row = "";
		System.out.println(question_id);
		while(rs.next()){
			for(int i = 0; i < rsmd.getColumnCount(); i++) {
				single_row += rs.getString(i+1) + "\t";
			}
			System.out.println(single_row);
			single_row = "";
			} 
		System.out.println("");
	}
	public static void main(String[] args) throws SQLException, IOException {
		//call create table function, create a table in local MySQL database	
		//CreateTable();
		//call load movie function, import txt file to the table
		//LoadMovie();
		
		//use the prepared statement to conduct sql query
		String q1 = "select directors, title from test.movie where title = ?";
		String q2 = "select title_type, title from test.movie where title_type = ?";
		String q3 = "select title, title_type, year from test.movie where title_type = ? and year = ?";
		String q4 = "select title, directors from test.movie where directors = ?";
		String q5 = "select title, directors, year from test.movie where directors = ? and year = ?";
		String q6 = "select count(*), year from test.movie group by year order by count(*) desc limit ?";
		String q7 = "select title, genres from test.movie where genres like ?";
		String q8 = "select title, genres, votes from test.movie order by votes desc limit ?";
		String q9 = "select title, genres, runtime, year from test.movie where genres like ? and year >? order by runtime desc limit ?";
		String q10 = "select avg(imdb_rating) from test.movie";
		String q11 = "select * from test.movie";
		
		PreparedStatement pstmt = null;
		//Q1
		pstmt = get_prepared_statement(q1);
		pstmt.setString(1, "A Walk to Remember");
		excute_query(pstmt, "Q1:");
		
		//Q2
		pstmt = get_prepared_statement(q2);
		pstmt.setString(1, "Documentary");
		excute_query(pstmt, "Q2:");
		

		//Q3
		pstmt = get_prepared_statement(q3);
		pstmt.setString(1, "Documentary");
		pstmt.setInt(2, 2000);
		excute_query(pstmt, "Q3:");
		
		//Q4
		pstmt = get_prepared_statement(q4);
		pstmt.setString(1, "Steven Spielberg");
		excute_query(pstmt, "Q4:");
		
		//Q5
		pstmt = get_prepared_statement(q5);
		pstmt.setString(1, "Steven Spielberg");
		pstmt.setInt(2, 2012);
		excute_query(pstmt, "Q5:");
		
		//Q6 TODO
		pstmt = get_prepared_statement(q6);
		pstmt.setInt(1, 1);
		excute_query(pstmt, "Q6:");

		//Q7
		pstmt = get_prepared_statement(q7);
		pstmt.setString(1, "%romance%");
		excute_query(pstmt, "Q7:");
		
		//Q8
		pstmt = get_prepared_statement(q8);
		pstmt.setInt(1, 10);
		excute_query(pstmt, "Q8:");

		//Q9
		pstmt = get_prepared_statement(q9);
		pstmt.setString(1, "%thriller%");
		pstmt.setInt(2,2005);
		pstmt.setInt(3, 1);
		excute_query(pstmt, "Q9:");
		
		//Q10, unknown values have already been converted to null in database
		//Therefore, null rating values have already been excluded when conducting aggregate function 
		pstmt = get_prepared_statement(q10);
		excute_query(pstmt, "Q10, average IMDB rating:");
		
		//Q11 select all rows and use the buffer writer to save the text file
		pstmt = get_prepared_statement(q11);
		ResultSet rs = pstmt.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		String single_row = "";
		FileWriter writer = new FileWriter("MyFile.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        System.out.println("Start downloading movie dataset...");
		while(rs.next()){
			for(int i = 0; i < rsmd.getColumnCount(); i++) {
				single_row += rs.getString(i+1) + ";";
			}
			bufferedWriter.write(single_row);
            bufferedWriter.newLine();
            single_row = "";
			} 
		System.out.println("Done!");
		System.out.println("Moive saved to:" + System.getProperty("user.dir") + "\\" + "MyFile.txt");
		bufferedWriter.close();
	}

}
