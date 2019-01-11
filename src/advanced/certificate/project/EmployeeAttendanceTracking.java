package advanced.certificate.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class EmployeeAttendanceTracking {

	private static final Logger logger = Logger.getLogger(EmployeeAttendanceTracking.class.getName());
	private static final String CONN_FILE = "resources/mysql.properties";
	private static String origin_input = null;
	private static String employee_name = null;
	private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");

	public static void main(String[] args) throws java.text.ParseException, FileNotFoundException {

		Properties p = new Properties();
		// load the property file
		try {
			p.load(new FileInputStream(new File(CONN_FILE)));
		} catch (IOException e) {
			logger.debug("Error reading " + CONN_FILE, e);
			System.exit(-1);
		}
		String db_url = p.getProperty("db_url");
		String user = p.getProperty("user");
		String psw = p.getProperty("password");
		sdf1.setLenient(false);
		sdf2.setLenient(false);
		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		try {
			Connection conn = DriverManager.getConnection(db_url, user, psw);
			int menu_selection = 0;
			System.out.println("");
			System.out.println("Please select one option from the following list: ");
			System.out.println("[1] Query the database and get the attendance for all employees for any day or month");
			System.out.println("[2] Query the database and get the attendance for the month or a day for a particular employee");
			System.out.println("[3] Query the database to get the list of employees along with the number of times they had less than 8 hours");
			String input = sc.next();
			menu_selection = Integer.parseInt(input);
			logger.info("user input: " + menu_selection);
			switch (menu_selection) {
			case 1:
				System.out.println("Please specify a day or a month to query the attendance for all employees:");
				System.out.println("For example, 2018-05-07 or 2018-05");
				origin_input = sc1.next();
				logger.info("User input: " + origin_input);
				if (origin_input.split("-").length == 2) {
					Date date = sdf2.parse(origin_input.trim());
					go_query(conn, origin_input.trim(), "1month");
				} else if (origin_input.split("-").length == 3) {
					Date date = sdf1.parse(origin_input.trim());
					go_query(conn, origin_input.trim(), "1day");
				} else {
					logger.info("Wrong input, program ended");
					System.exit(-1);
				}
				break;
			case 2:
				System.out.println("Please specify a day or a month and an an employee's initials to query");
				System.out.println("for example: 2018-11-28, I N");
				origin_input = sc1.nextLine();
				logger.info("User input: " + origin_input);
				if (origin_input.contains(",") && origin_input.split(",").length == 2) {
					String s[] = origin_input.split(",");
					employee_name = s[1].trim();
					if (s[0].trim().split("-").length == 2) {
						Date date = sdf2.parse(s[0].trim());
						go_query(conn, s[0].trim(), "2month");
					} else if (s[0].trim().split("-").length == 3) {
						Date date = sdf1.parse(s[0].trim());
						go_query(conn, s[0].trim(), "2day");
					} else {
						logger.info("Wrong input, program ended");
						System.exit(-1);
					}
				} else {
					logger.info("Wrong input format, please try again");
				}
				break;
			case 3:
				System.out.println("Please specify a week or a month of a year");
				System.out.println("for example: 2018, 24, week or 2018, 11, month");
				origin_input = sc1.nextLine();
				logger.info("User input: " + origin_input);
				if (origin_input.trim().contains(",") && origin_input.split(",").length == 3) {
					String converted_date = null;
					String s[] = origin_input.trim().split(",");
					try {
						if (s[2].trim().equals("week")) {
							// here use mysql yearweek function to query
							converted_date = s[0].trim() + s[1].trim();
							go_query(conn, converted_date, "3week");
						} else if (s[2].trim().equals("month")) {
							converted_date = s[0].trim() + "-" + s[1].trim();
							go_query(conn, converted_date, "3month");
						} else {
							logger.info("Wrong input format, please try again");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					logger.info("Wrong input format, please try again");
				}
				break;
			default:
				System.out.println("Invalid selection");
				break;
			}
			logger.info("Program ended");
			conn.close();
			sc.close();
			sc1.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	// prepare the sql query
	public static void go_query(Connection conn, String input_date, String case_op) throws FileNotFoundException {
		String basic_sql = "select emp_name, time, code FROM test.attendance_log AS a, test.emp_access_map AS b, test.emp AS c  "
				+ "where a.card_id = b.card_id " + "and b.emp_id = c.emp_id ";
		String q3_sql = "select emp_name, a.card_id, a.time checkin_time, a.code checkin_code, b.time checkout_time, b.code checkout_code, timediff(b.time, a.time) duration\r\n"
				+ "from attendance_log as a\r\n"
				+ "inner join emp_access_map on a.card_id = emp_access_map.card_id \r\n"
				+ "inner join emp on emp.emp_id = emp_access_map.emp_id\r\n"
				+ "inner join attendance_log as b on a.card_id = b.card_id and date(a.time) = date(b.time)\r\n"
				+ "where a.code = 'N' and b.code = 'X' and hour(abs(timediff(b.time,a.time))) < 8.5 ";
		PreparedStatement pstmt = null;
		String sql = null;
		switch (case_op) {
		// use the join table to display employee initials rather than employee id
		case "1day":
			sql = basic_sql + "and time between ? and ? ";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, input_date);
				pstmt.setString(2, input_date + " 23:59:59");
				execute_sql(pstmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "1month":
			sql = basic_sql + "and time like ? ";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, input_date + "%");
				execute_sql(pstmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "2day":
			sql = basic_sql + "and time between ? and ?" + " and emp_name = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, input_date);
				pstmt.setString(2, input_date + " 23:59:59");
				pstmt.setString(3, employee_name);
				execute_sql(pstmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// when finish, reset the golbal employee_name
			employee_name = null;
			break;
		case "2month":
			sql = basic_sql + "and time like ?" + " and emp_name = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, input_date + "%");
				pstmt.setString(2, employee_name);
				execute_sql(pstmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// when finish, reset the golbal employee_name
			employee_name = null;
			break;
		case "3week":
			sql = q3_sql + "and yearweek(date(a.time),1) = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, input_date);
				execute_sql(pstmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "3month":
			sql = q3_sql + "and a.time like ? ";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, input_date + "%");
				execute_sql(pstmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			System.exit(-1);
		}
	}

	// execute query
	public static void execute_sql(PreparedStatement pstmt) throws SQLException, FileNotFoundException {
		ResultSet rs = pstmt.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		String single_row = "";
		// display no match if result set is empty
		if (!rs.next()) {
			logger.info("No matched records found.");
			// display matched result
		} else {
			File file = new File("query_result.csv");
			PrintWriter pw = new PrintWriter(file);
			StringBuilder sb = new StringBuilder();
			// move cursor to the front, because !rs.next() has been executed once
			//write the column name for the first row
			rs.beforeFirst();
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				sb.append(rsmd.getColumnName(i + 1));
				if(i != rsmd.getColumnCount() -1) {
					sb.append(",");
				}
			}
			sb.append('\n');
			//move cursor to the front again and start to write data
			rs.beforeFirst();
			logger.info("Matched records:");
			while (rs.next()) {
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					single_row += rs.getString(i + 1) + "\t";
					sb.append(rs.getString(i + 1));
					if(i != rsmd.getColumnCount() -1) {
						sb.append(",");
					}
				}
				sb.append('\n');
				logger.info(single_row);
				single_row = "";
			}
			logger.info("Save the query reslut? Press 'y' to save, any other key to skip.");
			Scanner sc2 = new Scanner(System.in);
			String save_input = sc2.nextLine();
			logger.info("user input: " + save_input);
			if (save_input.trim().toUpperCase().equals("Y")) {
				pw.write(sb.toString());
				logger.info("Query result saved as query_result.csv file");
				pw.close();
				logger.info("Upload query reslut? Press 'y' to save, any other key to skip.");
				Scanner sc3 = new Scanner(System.in);
				String upload_input = sc3.nextLine();
				logger.info("user input: " + save_input);
				if (upload_input.trim().toUpperCase().equals("Y")) {
					
				}
			}
			else {
				//close input stream before deleting the csv file
				pw.close();
				file.delete();
			}
			sc2.close();
		}
	}

}
