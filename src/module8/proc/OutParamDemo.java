package module8.proc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class OutParamDemo {
	private static final Logger logger = Logger.getLogger(OutParamDemo.class.getClass());
	private final static String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	private final static String db_url = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false";	
	private final static String user = "root";
	private final static String psw = "336299";
	
	public static void main(String[] args) {
		try {
			Class.forName(jdbc_driver);
			try {
				Connection conn = DriverManager.getConnection(db_url,user,psw);
				//here we assume get_name script is stored in mysql
				try (CallableStatement proc = conn.prepareCall("Call get_name (?,?)");){
					String name = null;
					//set the id to search the associated student name, here we use 1
					proc.setInt(1, 1);
					proc.setString(2, name);
					name = proc.getString(2);
					if(name != null) {
						logger.debug("Name:" + name);
					}else {logger.debug("ID does not exist");}
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
}
}