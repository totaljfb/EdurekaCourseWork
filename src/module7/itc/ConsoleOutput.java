package module7.itc;

import org.apache.log4j.Logger;

public class ConsoleOutput {
	private static Logger logger = Logger.getLogger(ConsoleOutput.class.getName());
	
	private volatile int n;
	private volatile boolean b = true;
	
	public ConsoleOutput(int n) {
		this.n = n;
	}
	
	public synchronized void One2N() throws InterruptedException {
		if(!b) {
			logger.debug("Thread " + this.getClass().getSimpleName() + " starts to print 1 to N:" );
			for(int i = 1; i < n + 1; i++) {
				System.out.println(i);
				Thread.sleep(1000);
			}
			b = true;
			logger.debug("Thread " + this.getClass().getSimpleName() + " finishes printing 1 to N." );
			notify();
		}
		else { wait();}
	}
	
	public synchronized void N2One() throws InterruptedException {
		if(b) {
			logger.debug("Thread " + this.getClass().getSimpleName() + " starts to print N to 1:" );
			for(int i = n; i > 0; i--) {
				System.out.println(i);
				Thread.sleep(1000);
			}
			b = false;
			logger.debug("Thread " + this.getClass().getSimpleName() + " finishes printing N to 1." );
			notify();
		}
		else { wait();}
	}
}
