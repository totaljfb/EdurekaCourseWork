package module6.catalogue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CatalogueUtils {
	//this list stores all the book IDs
	private static ArrayList<String> BookIds = new ArrayList<>();
	//this is the hard coded output filename
	private static String newXML = "resources//module6//newcatalog.xml";
	//this method return the book item model, which has all the information of the book
	//use the bookitem class in case of any inquiry or modification in the future 
	public static BookItem getBookItem(Document model, String bookid) {
		BookItem bookitem = new BookItem();
		NodeList nodes = model.getElementsByTagName("book");	
		for(int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element)node;
				if(bookid.equals(element.getAttribute("id"))) {
					bookitem.bookId = bookid;
					bookitem.desc = element.getElementsByTagName("description").item(0).getTextContent();
					bookitem.genre = element.getElementsByTagName("genre").item(0).getTextContent();
					bookitem.price = element.getElementsByTagName("price").item(0).getTextContent();
					bookitem.author = element.getElementsByTagName("author").item(0).getTextContent();
					bookitem.publishDate = element.getElementsByTagName("publish_date").item(0).getTextContent();
					bookitem.title = element.getElementsByTagName("title").item(0).getTextContent();
				} 
			}
		}
		return bookitem;
	}
	//this method returns a list for all the book IDs
	public static ArrayList<String> getBookIds(Document model){
		
		NodeList nodes = model.getElementsByTagName("book");
		
		for(int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element)node;
				BookIds.add(element.getAttribute("id"));
			}
		}
		return BookIds;
	}
	//this method modify the price and save to a new XML file
	public static boolean modify(BookItem bookitem, String newPrice, Document model, String newXML) throws TransformerException {
		boolean result = false;
		NodeList nodes = model.getElementsByTagName("book");
		for(int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element)node;
				if(bookitem.bookId.equals(element.getAttribute("id"))) {
					//update price
					element.getElementsByTagName("price").item(0).setTextContent(newPrice);
					//save to new XML file
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(model);
					StreamResult rs = new StreamResult(new File(newXML));
					transformer.transform(source, rs);
					result = true;
				}
			}
		}
		return result;
	}
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		if(args.length != 1) {
			System.out.println("Usage: module6.CatalogueUtils [xml_filename]");
			System.exit(-1);
		}
		
		DocumentBuilderFactory parserFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = parserFactory.newDocumentBuilder();
		Document model = builder.parse(args[0]);
		
		getBookIds(model);
		if(BookIds.size() > 0) {
			System.out.println("Here are the book IDs:");
			for(String bookid: BookIds) {
				System.out.println(bookid);
			}
		}
		else {
			System.out.println("Book IDs not found.");
			System.exit(-1);
		}
		//let the user select a book id to modify the price
		System.out.println("");
		System.out.println("Enter a book ID which you want to make a change on price:");
		Scanner sc = new Scanner(System.in);
		String bookid = sc.nextLine();
		//validate the input
		if(BookIds.contains(bookid)) {
			System.out.println("You have input the book ID: " + bookid);
			BookItem bookitem = getBookItem(model, bookid);
			System.out.println("The current price of this book is: $" + bookitem.price);
			System.out.println("Enter a new price for the book:");
			String newPrice = sc.nextLine();
			//call the modification method
			boolean result = modify(bookitem, newPrice, model, newXML);
			sc.close();
			if(result) {
				System.out.println("New price $" + newPrice +" updated to file: " + newXML);
			}
			else {
				System.out.println("New price updated fail");
				System.exit(-1);
			}
			
		}
		else {
			System.out.println("No such book ID found in the list, please re-run the program.");
			sc.close();
			System.exit(-1);
		}		
		
	}
}
