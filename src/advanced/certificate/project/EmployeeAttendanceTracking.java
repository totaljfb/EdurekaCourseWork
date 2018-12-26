package advanced.certificate.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import org.apache.log4j.Logger;


import module9.tcp.sockets.Client;

public class EmployeeAttendanceTracking {

	private static final Logger logger = Logger.getLogger(Client.class.getName());
	private static final String CONN_FILE = "resources/mysql.properties";
	private static final DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-mm");
	private static final DateTimeFormatter formatter2 = DateTimeFormatter.ISO_LOCAL_DATE;
	private static List<String> input_list = new ArrayList<>();
	private static String origin_input = null;
	private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
	
	public static void main(String[] args) {
		
		Properties p = new Properties();
		//load the property file
		try {
			p.load(new FileInputStream(new File(CONN_FILE)));
		}catch (IOException e) {
			logger.debug("Error reading " + CONN_FILE, e);
			System.exit(-1);
		}
		String db_url = p.getProperty("db_url");
		String user = p.getProperty("user");
		String psw = p.getProperty("password");
		sdf1.setLenient(false);
		sdf2.setLenient(false);
		try {
			Connection conn = DriverManager.getConnection(db_url,user,psw); 
			Scanner sc = new Scanner(System.in);
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
				try{
					menu_selection = sc.nextInt();
				}catch (InputMismatchException e) {
					logger.info("User input: " + sc.next());
					System.out.println("Please pick a numebr in between 1 and 6, program ended");
					break;
				}
				logger.info("user input: " + menu_selection);
				switch(menu_selection) {
				case 1: System.out.println("Please specify a day or a month to query the attendance for all employees:");
						System.out.println("For example, 2018-05-07 or 2018-05");
						origin_input = sc.next();
						logger.info("User input: " + origin_input);
						try
						break;
				case 2: System.out.println("Please specify a day or a month and an an employee's initials to query");
						System.out.println("for example: 2018-11-28, I N");
						//read new line by reading the "Enter" which is hit by user
						sc.nextLine();
						//read the input line
						origin_input = sc.nextLine();
						logger.info("User input: " + origin_input);
						if(origin_input.contains(",") && origin_input.split(",").length == 2) {
							String s[] = origin_input.split(",");
							input_list.clear();
							input_list.add(s[0].trim());
							input_list.add(s[1].trim());
							go_query(2, conn, input_list, origin_input);
						}
						else {
							logger.info("Wrong input format, please try again");
						}
						break;
				case 3: System.out.println("Please specify a week or a month of a year");
						System.out.println("for example: 2018, 24, week or 2018, 11, month");
						sc.nextLine();
						//read the input line
						origin_input = sc.nextLine();
						logger.info("User input: " + origin_input);
						if(origin_input.trim().contains(",") && origin_input.split(",").length == 3) {
							String converted_date = null;
							String s[] = origin_input.trim().split(",");
							try {
								if(s[2].trim().equals("week")) {
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
									Calendar cal = Calendar.getInstance();
									cal.setWeekDate(Integer.parseInt(s[0].trim()), Integer.parseInt(s[1].trim()), 1);
									converted_date = sdf.format(cal.getTime()); 
									input_list.clear();
									input_list.add(converted_date);
								}
								else if(s[2].trim().equals("month")) {
									converted_date = s[0].trim() + "-" + s[1].trim();
									input_list.clear();
									input_list.add(converted_date);
								}
								else {
									logger.info("Wrong input format, please try again");
								} 
							}catch(Exception e){
								e.printStackTrace();
							}
							go_query(3, conn, input_list, origin_input);
						}
						else {
							logger.info("Wrong input format, please try again");
						}
						break;
				case 4: System.out.println(menu_selection);
						break;
				case 5: System.out.println(menu_selection);
						break;
				default: System.out.println("Invalid selection");
						break;
				}
			}while(menu_selection!=6 );
				logger.info("Program ended");
				sc.close();
				conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
				System.exit(-1);
		}
	}
	
	//prepare the sql query	
	public static void go_query(int case_number, Connection conn, List<String> input_list, String origin_input) {
		String parse_result = null;
		parse_result = parse_date(input_list.get(0));
		String basic_sql = "select emp_name, time, code FROM test.attendance_log AS a, test.emp_access_map AS b, test.emp AS c  "
							+ "where a.card_id = b.card_id "
							+ "and b.emp_id = c.emp_id ";
		PreparedStatement pstmt = null;
		String sql = null;
		switch(case_number + parse_result) {
		//use the join table to display employee initials rather than employee id
		case "1day":sql = basic_sql + "and time between ? and ? ";
					try {
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, input_list.get(0));
						pstmt.setString(2, input_list.get(0) + " 23:59:59");
						execute_sql(pstmt); 
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
		case "1month":sql = basic_sql + "and time like ? ";
					  try {
						  pstmt = conn.prepareStatement(sql);
						  pstmt.setString(1, input_list.get(0) + "%");
						  execute_sql(pstmt); 
					  } catch (SQLException e) {
						  e.printStackTrace();
						}
					break;
		case "2day":sql = basic_sql + "and time between ? and ?" + " and emp_name = ?";  
					try {
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, input_list.get(0));
						pstmt.setString(2, input_list.get(0) + " 23:59:59");
						pstmt.setString(3, input_list.get(1));
						execute_sql(pstmt); 
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
		case "2month":sql = basic_sql + "and time like ?" + " and emp_name = ?";
		  try {
			  pstmt = conn.prepareStatement(sql);
			  pstmt.setString(1, input_list.get(0) + "%");
			  pstmt.setString(2, input_list.get(1));
			  execute_sql(pstmt); 
		  } catch (SQLException e) {
			  e.printStackTrace();
			}
		break;
		default: System.exit(-1);
		}
	}
	
	//validate the input date format
	public static String parse_date(String date) {
		long count_ =  date.chars().filter(ch -> ch == '-').count();
		String parse_result = null;
		if(count_ == 1L) {
			try {
				formatter1.parse(date);
				parse_result = "month";
			}catch(DateTimeParseException e) {
				logger.info("Wrong format or invalid date, please check your date and use yyyy-mm-dd or yyyy-mm format");
				logger.info("Program ended");
				System.exit(-1);
			}
		}
		else if(count_ == 2L) {
			try {
				formatter2.parse(date);
				parse_result = "day";
			}catch(DateTimeParseException e) {
				logger.info("Wrong format or invalid date, please check your date and use yyyy-mm-dd or yyyy-mm format");
				logger.info("Program ended");
				System.exit(-1);
			}
		}
		else {
			logger.info("Wrong format or invalid date, please check your date and use yyyy-mm-dd or yyyy-mm format");
			logger.info("Program ended");
			}
		return parse_result;
	}
	
	//execute query
	public static void execute_sql(PreparedStatement pstmt) throws SQLException {
		ResultSet rs = pstmt.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		String single_row = "";
		//display no match if result set is empty
		if(!rs.next()) {
			logger.info("No matched records found.");
		//display matched result
		}else {
			//move cursor to the front, because !rs.next() has been executed once
			rs.beforeFirst();
			logger.info("Matched records:");
			while(rs.next()){
				for(int i = 0; i < rsmd.getColumnCount(); i++) {
					single_row += rs.getString(i+1) + "\t";
				}
				logger.info(single_row);
				single_row = "";
				}
		}
		
	}
	
	
}
