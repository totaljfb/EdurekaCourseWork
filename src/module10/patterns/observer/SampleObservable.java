package module10.patterns.observer;

import java.util.Observable;
import java.util.Observer;

public class SampleObservable extends Observable {
	int data = 0;
	
	public void setData(int data, Observer ob){
		//only consume odd numbers
		if(data%2 == 0) {
			this.data = data;
			this.setChanged();
			//notify specific observer
	        this.notifyObservers(ob);
		}
	}
}
