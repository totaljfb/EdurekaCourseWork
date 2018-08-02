package com.edureka.advanced.java.a3.q4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//extend the thread class so that the listers list can be synchronized
public class EventSource extends Thread{
	private List<EventListener> listeners = new ArrayList<>();
	public void RegisterListener(EventListener listener) {
		synchronized(listeners) {
			this.listeners.add(listener);
		}
	}
	//override the run method
	@Override
	public void run() {
		Random r = new Random();
		while(true) {
			//get a random double number between 0 and 1
			double expected_number = r.nextDouble();
			//use the Double class to get the integer part
			//time by 10000 to get the 10000th decimal place 
			Double a = expected_number*10000;
			Integer i = a.intValue();
			//if it is a even number, create the event object and call the listener
			if(i%2 == 0) {
				int expected_digit = i%10;
				Event evt = new Event(expected_number,expected_digit);
				synchronized(listeners) {
					for(EventListener listener: listeners) {
						listener.NotifyEvent(evt);
					}
				}
			}
		}
	}
}

