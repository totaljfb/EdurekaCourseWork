package com.edureka.advanced.java.a3.q4;

public class EventDemo {
	//Demo for the program
	public static void main(String[] args) {
		//create an event source object and register the event listener
		EventSource esource = new EventSource();
		EventListener numberfoundlistener = new NumberFoundListener();
		esource.RegisterListener(numberfoundlistener);
		//start the thread
		esource.start();
	}

}

//implement a class for the event listener interface
class NumberFoundListener implements EventListener {
	@Override
	//when event happens, print out the expected random number
	public void NotifyEvent(Event evt) {
		System.out.println("Expected number is found: " + evt.getExpected_number());
		System.out.println("The digit at the 10000th decimal place is : " + evt.getExpected_digit());
		//output every 1 second
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}	