package module6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public final class TextFileHandler {
	
	private static String file_name = "resources/countries.csv";
	private static HashMap<String, String> table = new HashMap<String, String>();
	private static HashMap<String, String> getTable(){
		
		
		return table;
	}
	public static void loadFile() throws FileNotFoundException, IOException{
		try(BufferedReader br = new BufferedReader(new FileReader(new File(file_name)));){
			String line = null;
			while((line = br.readLine()) != null) {
				StringTokenizer tokens = new StringTokenizer(line,",");
				String val = null, key = null;
				//first token key and second token value
				while(tokens.hasMoreTokens()) {
					val = tokens.nextToken().trim();
					if(!table.containsKey(key)) {
						key = val;
						//first put the key
						table.put(key, null);
					}
					//then look for the key and update the value
					else table.put(key,val);
				}
			}
		}
	}
	
	public static void dumpTable(Map<String, String> map) {
		Set<String> keys = map.keySet();
		Iterator<String> it = keys.iterator();
		
		while(it.hasNext()) {
			String key = it.next();
			System.out.println("Key: " + key + " Value: " + table.get(key));
		}
	}
	
	public static void printFirst10Lines() throws FileNotFoundException, IOException{
		try(BufferedReader br = new BufferedReader(new FileReader(new File(file_name)));){
			int i = 0;
			do {
				i++;
				System.out.println("Line " + i + ": " + br.readLine());
			}while(i < 10);
		}
	}
	
	public static void writeToFile(String newFilename, char countryNameBeginningWith) 
			throws FileNotFoundException, IOException {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File(newFilename)));){
			for(String key: table.keySet()) {
				if(key.startsWith(String.valueOf(countryNameBeginningWith))){
					bw.write(key + " " + table.get(key) + "\n");
				}
			}
			bw.close();
			System.out.println("Write to file completed.");
		}
	}
	
	public static void main(String[] args) {
		try {
			//loadFile method will store the countries as keys
			//and the capitals as values to a table
			//this method must be executed before other methods
			TextFileHandler.loadFile();
			String new_write_file = "resources/countries_s.csv";;
			char header = 'S';
			TextFileHandler.writeToFile(new_write_file, header);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}
