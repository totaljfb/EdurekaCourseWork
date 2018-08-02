import java.util.Date;

public class ThreadTime implements Runnable {
	public void run() {
		Date d1 = new Date();
		System.out.println("Current date and time is :" + d1);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadTime tt = new ThreadTime();
		Thread t1 = new Thread(tt);
		t1.start();

		
	}

}
