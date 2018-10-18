package module9.url;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MyFileDownloader {

	private static String spec = "http://www.oracle.com/events/global/en"
			+ "/java-outreach/resources/java-a-beginners-guide-1720064.pdf";
	
	public static void main(String[] args) {
		if(args.length == 1) {
			File file = new File(args[0]);
			//validate path first
			if(file.isDirectory()) {
				try {
					URL url = new URL(spec);
					int count = -1;
					byte[] buffer = new byte[20480];
					InputStream istream = url.openStream();
					//get full file name from url
					String filename = spec.substring(spec.lastIndexOf('/')+1, spec.length() );
					//construct the file with given path
					FileOutputStream fos = new FileOutputStream(file.getPath() + "\\"+ filename);
					while((count = istream.read(buffer)) != -1) {
						fos.write(buffer,0,count);
					}
					fos.flush();
					fos.close();
					istream.close();
				}catch (MalformedURLException e) {
					System.out.println("Malformed URL spec.");
					e.printStackTrace();
				}catch (IOException e) {
					System.out.println("Error reading streams.");
					e.printStackTrace();
				}
			}else {
				System.out.println("[path] is not valid, please try again.");
			}
			
		}else {
			System.out.println("Usage: java MyFileDownloader [path]");
			System.out.println("[path] is the folder path where the pdf file is downloaded");
			System.exit(-1);
		}	
	}

}
