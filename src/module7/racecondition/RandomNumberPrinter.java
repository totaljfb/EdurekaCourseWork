package module7.racecondition;

import org.apache.log4j.Logger;

public class RandomNumberPrinter {
	
	private static Logger logger = Logger.getLogger(RandomNumberPrinter.class.getName());
	//make changes on this method so that the numbers will be printed thread by thread
	//as well as the output log info
	public synchronized void printRandomNumbers(int n, Object o) {
		logger.debug(this.getClass().getName() + " entered.");
		logger.debug(o.getClass().getSimpleName() + " running...");
		for(int i = 0; i < n; i++) {
			System.out.println((int)(Math.random()*1000));
		}
		logger.debug(o.getClass().getSimpleName() + " exiting...");
		logger.debug(this.getClass().getName() + " exited.");
	}
}
