package module10.patterns.observer;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class randwatcher implements Observer{
	
	@Override
	public void update(Observable o, Object arg) {
		SampleObservable example = (SampleObservable)o;
		System.out.println("example.data changed, the new value of data is " + example.data);
	}

	public static void main(String[] args) {
		if(args.length == 1) {
			int n = 0;
			Random r = new Random();
			try {
				n = Integer.parseInt(args[0]);
				randwatcher watcher = new randwatcher();
				//make the observer to be registered to two subjects
				SampleObservable example1 = new SampleObservable();
				SampleObservable example2 = new SampleObservable();
				example1.addObserver(watcher);
				example2.addObserver(watcher);
				for(int i = 0; i < n; i++) {
					example1.setData(r.nextInt(), watcher);
					example2.setData(r.nextInt(), watcher);
				}
			}catch(NumberFormatException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("The expected argument is an integer, please try again");
			System.exit(-1);
		}
	}

}
