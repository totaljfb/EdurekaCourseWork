package module9.tcp.sockets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import org.apache.log4j.Logger;

public final class Client {
	
	private static final Logger logger = Logger.getLogger(Client.class.getName());
	//properties file for connections
	private static final String CONN_FILE = "resources/conn.properties";
	
	public static void main(String[] args) {
		System.out.println("Input 'quit' anytime to quit program.");
		Properties p = new Properties();
		//load the property file
		try {
			p.load(new FileInputStream(new File(CONN_FILE)));
		}catch (IOException e) {
			logger.debug("Error reading " + CONN_FILE, e);
			System.exit(-1);
		}
		
		String host = p.getProperty("host");
		int port = Integer.valueOf(p.getProperty("tcp_port"));
		
		//create a client socket
		try(Socket client = new Socket(host,port);){
			//open a stream for console input
			try(BufferedReader input = new BufferedReader(new InputStreamReader(System.in));){
				logger.debug("Connected to host: " + host + ":" + port);
				try(PrintWriter out = new PrintWriter(client.getOutputStream(),true);
						BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));){
							String instr = null;
							do{
								System.out.print("Enter text to sent to server -> ");
								//send a message to the server
								String str = input.readLine();
								out.println(str);
								instr = str;
								System.out.println("*** Feedback from server -> " + in.readLine());
							}while(!instr.equals("quit"));
							System.out.println("Program terminated.");
						}
			}
		} catch (UnknownHostException e) {
			logger.error("Could not connect to host.", e);
		} catch (IOException e) { 
			logger.error("Lost connection to host due to I/O error.", e);
		} 

	}

}
