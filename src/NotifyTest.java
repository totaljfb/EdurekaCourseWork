class NotifyThread extends Thread {
	public NotifyThread(String name) {
		super(name);
	}

	public void run() {
		try {
			sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized (NotifyTest.flag) {
			NotifyTest.flag[0] = "false";
			NotifyTest.flag.notifyAll();
		}
	}
};

class WaitThread extends Thread {
	public WaitThread(String name) {
		super(name);
	}

	public void run() {
		synchronized (NotifyTest.flag) {
			while (NotifyTest.flag[0] != "false") {
				System.out.println(getName() + " begin waiting!");
				long waitTime = System.currentTimeMillis();
				try {
					NotifyTest.flag.wait();

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				waitTime = System.currentTimeMillis() - waitTime;
				System.out.println("wait time :" + waitTime);
			}
			System.out.println(getName() + " end waiting!");
		}
	}
}
public class NotifyTest {
	static String flag[] = { "true" };

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Main Thread Run!");
		//NotifyTest test = new NotifyTest();
		NotifyThread notifyThread = new NotifyThread("notify01");
		WaitThread waitThread01 = new WaitThread("waiter01");
		WaitThread waitThread02 = new WaitThread("waiter02");
		WaitThread waitThread03 = new WaitThread("waiter03");
		notifyThread.start();
		waitThread01.start();
		waitThread02.start();
		waitThread03.start();
	}

}
