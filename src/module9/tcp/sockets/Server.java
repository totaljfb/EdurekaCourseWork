package module9.tcp.sockets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import org.apache.log4j.Logger;

public final class Server {
	private static final Logger logger = Logger.getLogger(Server.class.getName());
	//properties file for connections
	private static final String CONN_FILE = "resources/conn.properties";
	
	public static void main(String[] args) {
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(new File(CONN_FILE)));
		}catch (IOException e) {
			logger.error("Error reading " + CONN_FILE);
			e.printStackTrace();
			System.exit(-1);
		}
		//in the demo, Constants class was used but I don't know which package it is from
		//instead, just the variable name is used here
		int port = Integer.valueOf(p.getProperty("tcp_port"));
		
		try(ServerSocket server = new ServerSocket(port);){
				while(true) {
				logger.debug("Waiting for connection...");
				//wait for connection, this is blocking call, i.e waits for new connections
				Socket client = server.accept();
				new Thread(new RequestProcessingThread(client)).start();
				logger.debug("Connected to " + client.toString());
				}
		}catch (IOException e) {
			logger.error("Lost connection to host due to I/O error.");
			e.printStackTrace();
		}		
		}
	}

