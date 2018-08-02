class Notify implements Runnable{
	public void run() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		synchronized(cardealer.flag) {
			cardealer.flag[0] = "true";
			cardealer.flag.notifyAll();
		}
		
	}
}

class Wait implements Runnable{
	public void run() {
		synchronized(cardealer.flag) {
			while(cardealer.flag[0] != "true") {
				System.out.println("Begin manufacturing cars...");
				try {
					cardealer.flag.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Cars completed!");
		}
		
	}
}
public class cardealer {
	public static String flag[] = {"false"};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Notify notify = new Notify();
		Wait wait = new Wait();
		Thread notify_thread = new Thread(notify);
		Thread wait_thread = new Thread(wait);
		notify_thread.start();
		wait_thread.start();
	}

}
