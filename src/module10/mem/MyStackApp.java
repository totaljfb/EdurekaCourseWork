package module10.mem;

public class MyStackApp {

	public static void main(String[] args) throws MyStackException, InterruptedException{
		final int volume = 20000;
		MyStack<Integer> stack = new MyStack<Integer>(volume);
		System.out.println("Pushing " + volume + " elements in a stack");
		for(int i = 0; i < volume; i++) {
			stack.push(new Integer(i));
		}
		System.out.println("Poping " + volume + " elements from a stack");
		for(int i = 0; i < volume; i++) {
			stack.pop();
		}
		//wait for a while
		System.out.println("Waiting fro 5 minutes to exit...");
		Thread.sleep(5*60*1000);		
	}

}
