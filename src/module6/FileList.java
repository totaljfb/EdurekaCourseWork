package module6;

import java.io.File;
import java.io.FilenameFilter;

public class FileList {

	public static void main(String[] args){
		try {
			//args[0]: dir path, args[1]: file extension
			File path = new File(args[0]);
			//use FilenameFilter interface to filer the result
			String[] list = path.list(new fileFilter(args[1]));
			if(list.length == 0) {
				System.out.println(args[0] + " doesn't have any file with extension " + args[1]);
			}
			else {
				for(String s:list) {
					System.out.println(s);
				}
			}
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("You need to input 2 parameters, please try again.");
				System.out.println("1st parameter: directory path, 2nd parameter: file extension filter");
				System.out.println("Example: c:\\\\temp  txt");
		}
	}
}
//implement the FilenameFilter interface with a file extension filter
class fileFilter implements FilenameFilter{
	
	private String extension;
	
	public fileFilter(String extension) {
		this.extension = extension.toLowerCase();
	}
	//callback structure
	public boolean accept(File dir, String name) {
		return name.toLowerCase().endsWith(extension);
	}
	
}