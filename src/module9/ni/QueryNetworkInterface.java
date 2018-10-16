package module9.ni;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.log4j.Logger;

public class QueryNetworkInterface {
	
	private static Logger logger = Logger.getLogger(QueryNetworkInterface.class);
	public static void main(String[] args) {
		try {
			Enumeration<NetworkInterface> ni = NetworkInterface.getNetworkInterfaces();
			NetworkInterface element = null;
			InetAddress address = null;
			while(ni.hasMoreElements()) {
				element = ni.nextElement();
				logger.info("Network Interface: " + element.getDisplayName() + ". Interface is running? " + YorN(element.isUp()));
				logger.info("The network interface has the following IP addresses:");
				Enumeration<InetAddress> address_list = element.getInetAddresses();
				while(address_list.hasMoreElements()) {
					address = address_list.nextElement();
					logger.info("InetAddress -> " + address.getHostAddress() + ". Support multicast? " + YorN(address.isMulticastAddress()));
				}
			}
		}catch (SocketException e) {
			logger.info("SocketException", e);
		}
	}
	
	public static String YorN(boolean value) {
		if(value) {
			return "Yes";
		}
		else {return "No";}
	}
}


