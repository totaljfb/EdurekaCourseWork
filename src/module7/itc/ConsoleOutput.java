package module7.itc;

import org.apache.log4j.Logger;

public class ConsoleOutput {
	private static Logger logger = Logger.getLogger(ConsoleOutput.class.getName());
	
	private volatile int n;
	
	public ConsoleOutput(int n) {
		this.n = n;
	}
	
	public void One2N() throws InterruptedException {
		logger.debug("Starts to print 1 to N:" );
		for(int i = 1; i < n + 1; i++) {
			System.out.println(i);
			Thread.sleep(500);
		}
		logger.debug("Finishes printing 1 to N." );
	}
	
	public void N2One() throws InterruptedException {
		logger.debug("Starts to print N to 1:" );
		for(int i = n; i > 0; i--) {
			System.out.println(i);
			Thread.sleep(500);
		}
		logger.debug("Finishes printing N to 1." );
	}

}
