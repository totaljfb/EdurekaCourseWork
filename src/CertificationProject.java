import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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
	                              " imdb_rating VARCHAR(255), " + 
	                              " runtime VARCHAR(255), " +
	                              " year VARCHAR(255), " +
	                              " genres VARCHAR(255), " +
	                              " votes VARCHAR(255) ," +
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
					else {
						sql += "\"" + Movie[i] + "\",";
					}
				}
				try {
					statement.executeUpdate(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Loading data completed.");
	}
	public static void main(String[] args) {
		//CreateTable();
		LoadMovie();
		
//		String q1 = "select directors, title from test.movie where title = ?";
//		
//		try {
//			Class.forName(jdbc_driver);
//			try {
//				Connection conn = DriverManager.getConnection(db_url,user,psw);
//				PreparedStatement pstmt = conn.prepareStatement(q1);
//				pstmt.setString(1, "A Walk to Remember");
//				ResultSet rs = pstmt.executeQuery();
//				while(rs.next()){  
//					System.out.println(rs.getString(1)+"\t"+rs.getString(2));  
//					} 
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}

		
	}

}
