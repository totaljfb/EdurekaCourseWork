package module8.query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ParameterisedQuery {
	
	private final static String db_url = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false";	
	private final static String user = "root";
	private final static String psw = "root";

	public static void main(String[] args) {
		if(args.length == 1) {
			try {
				Connection conn = DriverManager.getConnection(db_url,user,psw);
				//use prepared statement for getting the argument
				PreparedStatement pstmt = null;
				String sql = "select id, name from test.student where name like ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, args[0]);
				ResultSet rs = pstmt.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				String single_row = "";
				//display no match if resultset is empty
				if(!rs.next()) {
					System.out.println("No matched student found.");
				//display matched result
				}else {
					//move cursor to the front, because !rs.next() has been executed once
					rs.beforeFirst();
					System.out.println("Here is the search result:");
					while(rs.next()){
						for(int i = 0; i < rsmd.getColumnCount(); i++) {
							single_row += rs.getString(i+1) + "\t";
						}
						System.out.println(single_row);
						single_row = "";
						}
				} 
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Useage: java ParameterisedQuery [x], x is a wildcard for student name.");
			System.out.println("Example: java ParameterisedQuery A%, search for student whose name "
					+ "starts with A, like Alex, Andy, etc.");
			System.exit(-1);
		}
	}

}
