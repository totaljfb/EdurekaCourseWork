package module8.proc;

import java.sql.Connection;
import java.sql.DriverManager;

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
			}
	}

}
}