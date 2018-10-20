package module9.url;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

public class MyFileDownloader {

	private static String spec = "http://www.oracle.com/events/global/en"
			+ "/java-outreach/resources/java-a-beginners-guide-1720064.pdf";
	private static float percent;
	private static int number_of_backspace;
	
	public static void main(String[] args) {
		if(args.length == 1) {
			File file = new File(args[0]);
			//validate path first
			if(file.isDirectory()) {
				try {
					URL url = new URL(spec);
					int count = -1;
					byte[] buffer = new byte[20480];
					URLConnection conn = url.openConnection();
					int filesize = conn.getContentLength();
					InputStream istream = url.openStream();
					//get full file name from url
					String filename = spec.substring(spec.lastIndexOf('/')+1, spec.length() );
					//construct the file with given path, always overwrite
					FileOutputStream fos = new FileOutputStream(file.getPath() + "\\"+ filename, false);
					while((count = istream.read(buffer)) != -1) {
						fos.write(buffer,0,count);
						print_progress(count,filesize);
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
	//need to use command line to see the effect, does not work in IDE
	public static void print_progress(int count, int filesize) {
		for(int i = 0; i< number_of_backspace; i++) {
			System.out.print("\b");
		}
		percent = ((float)count)/((float)filesize)*100f + percent;
		DecimalFormat df = new DecimalFormat("###.##");
		System.out.println(df.format(percent) + "%");
		number_of_backspace = df.format(percent).length() + 1;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
