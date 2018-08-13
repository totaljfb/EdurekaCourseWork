package module6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;

public final class SerializationDemo {
	
	public static void store(List<Person> list, String filename) throws FileNotFoundException, IOException{
		try(ObjectOutputStream outstream =
				new ObjectOutputStream(new FileOutputStream(filename));){
			outstream.writeObject(list);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<Person> load(String filename) 
			throws FileNotFoundException, IOException, ClassNotFoundException{
		try(ObjectInputStream instream = new ObjectInputStream(new FileInputStream(filename));){
			return (List<Person>)instream.readObject();
		}
	}
	
	public static void main(String[] args) {
			if(args.length != 2) {
				System.out.println("Usage: java module6.SerializationDemo Filename mode");
				System.exit(-1);
			}
			Person p1 = new Person("Karl Pearson", 79, "United Kingdom"),
					p2 = new Person("john Tukey", 85, "United States"),
					p3 = new Person("Jweeta Malayya", 21, "United States"),
					p4 = new Person("Jaya Sureya", 22, "India");
			
			Vector<Person> inlist = new Vector<Person>();
			inlist.add(p1);
			inlist.add(p2);
			inlist.add(p3);
			inlist.add(p4);
			
			try {
				//store the list to a file
				if("store".equalsIgnoreCase(args[1])){
					SerializationDemo.store(inlist, args[0]);
				} else {
					//load the list from a file
					List<Person> outlist = SerializationDemo.load(args[0]);
					for(Person p: outlist)
						System.out.println(p.toString());
				}							
			}catch(IOException|ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

}

class Person implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -545234235545L;
	
	protected String name = null;
	protected Integer age = null;
	protected transient String location = null;
	
	public Person() {}
	public Person(String name, Integer age, String location) {
		this.age = age;
		this.name = name;
		this.location = location;
	}
	
}