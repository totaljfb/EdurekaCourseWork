package advanced.certificate.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import module9.tcp.sockets.Client;

public class EmployeeAttendanceTracking {

	private static final Logger logger = Logger.getLogger(Client.class.getName());
	private static final String CONN_FILE = "resources/mysql.properties";
	
	public static void main(String[] args) {
		
		Properties p = new Properties();
		//load the property file
		try {
			p.load(new FileInputStream(new File(CONN_FILE)));
		}catch (IOException e) {
			logger.debug("Error reading " + CONN_FILE, e);
			System.exit(-1);
		}
		String host = p.getProperty("host");
		int port = Integer.valueOf(p.getProperty("port"));
		String user = p.getProperty("user");
		String psw = p.getProperty("password");

	}

}
