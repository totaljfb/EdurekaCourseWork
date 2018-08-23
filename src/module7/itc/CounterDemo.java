package module7.itc;

import org.apache.log4j.Logger;

public class CounterDemo {
	
	private static Logger logger = Logger.getLogger(CounterDemo.class.getName());
	//method to validate the argument
	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {	
		if(args.length == 1 && isInteger(args[0])) {
			int n = Integer.parseInt(args[0]);
			logger.debug("Entering programme...");
			ConsoleOutput co = new ConsoleOutput(n);
			Thread t1 = new Thread(new Task1(co));
			Thread t2 = new Thread(new Task2(co));
			t1.start();
			t2.start();
			t1.join();
			t2.join();
			logger.debug("Exiting programme...");
			
		}
		else {
			System.out.println("Usage: java CounterDemo [n]");
			System.out.println("n is an integer");
			System.exit(-1);
			}
	}

}

class Task1 implements Runnable{
	
	private ConsoleOutput co = null;
	
	public Task1(ConsoleOutput co) {
		this.co = co;
	}
	@Override
	public void run() {
		try {
			co.One2N();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
}

class Task2 implements Runnable{
	
	private ConsoleOutput co = null;
	
	public Task2(ConsoleOutput co) {
		this.co = co;
	}
	@Override
	public void run() {
		try {
			co.N2One();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
}
