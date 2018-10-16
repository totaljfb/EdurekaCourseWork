package module9.tcp.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.log4j.Logger;

public class RequestProcessingThread implements Runnable {
	private static final Logger logger = Logger.getLogger(RequestProcessingThread.class.getName());
	private Socket client;
	public RequestProcessingThread(Socket client) {
		this.client = client;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//get the client socket I/O stream
		try(PrintWriter out = new PrintWriter(client.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));){
			String clientstr = null;
			do{
				//print the message from the client
				String str = in.readLine();
				clientstr = str;
				logger.debug("*** Echoing client -> " + str);
				//send an acknowledgement to the client
				out.println("Acknowledged.");
				}while(!clientstr.equals("quit"));
		}catch (IOException ioe) {
			logger.info("IOExection ", ioe);
		}
		
	}
	
}
