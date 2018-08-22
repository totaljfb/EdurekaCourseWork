package module7.racecondition;

import org.apache.log4j.Logger;

public final class WithoutRaceConditionDemo {
	
	private static Logger logger = Logger.getLogger(WithoutRaceConditionDemo.class.getName());
	
	public static void main(String[] args) throws InterruptedException {
		logger.info("Entering programme...");
		RandomNumberPrinter rnp = new RandomNumberPrinter();
		Thread t1 = new Thread(new Task1(rnp));
		t1.start();
		Thread t2 = new Thread(new Task2(rnp));
		t2.start();
		
		t1.join();
		t2.join();
		logger.info("Exiting programme...");

	}
	
	private static class Task1 implements Runnable{
		private RandomNumberPrinter rnp = null;
		
		public Task1(RandomNumberPrinter rnp){
			this.rnp = rnp;		
		}

		@Override
		public void run() {
			rnp.printRandomNumbers(5, this);
		}
	}
	
	private static class Task2 implements Runnable{
		private RandomNumberPrinter rnp = null;
		
		public Task2(RandomNumberPrinter rnp){
			this.rnp = rnp;		
		}

		@Override
		public void run() {
			rnp.printRandomNumbers(5, this);
		}
	}
}
