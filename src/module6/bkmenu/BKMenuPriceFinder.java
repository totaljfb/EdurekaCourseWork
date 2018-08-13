package module6.bkmenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BKMenuPriceFinder {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		if(args.length != 2) {
			System.out.println("Usage: java module6.BkMenuPriceFinder [xml_filename] [item_name]");
			System.exit(-1);
		}
		
		//get the SAX parser factory instance
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		//get the parser from the factory
		SAXParser parser = parserFactory.newSAXParser();
		BreakfastMenuXMLHandler handler = new BreakfastMenuXMLHandler();
		//call the SAX parser
		parser.parse(args[0], handler);
		//iterate the menuItems to check if the name equals the input
		boolean itemfound = false;
		for (BkMenuItem item: handler.menuItems) {
			if(item.name.equals(args[1])) {
				System.out.println("The price of the item " + args[1] + " is " + item.price);
				itemfound = true;
			}
		}
		//if no match, print not found message
		if(!itemfound) {System.out.println("Item not found");}
	}	
}

class BreakfastMenuXMLHandler extends DefaultHandler{
	
	protected List<BkMenuItem> menuItems = new ArrayList<>();
	protected BkMenuItem item = null;
	protected String content = null;
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
		switch(qName) {
			case "food":
			item = new BkMenuItem();
			break;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException{
		switch(qName) {
			case "food":
				menuItems.add(item);
				break;
			case "name":
				item.name = content;
				break;
			case "price":
				item.price = content;
				break;
			case "description":
				item.description = content;
				break;
			case "calories":
				item.calories = content;
				break;			
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException{
		content = String.copyValueOf(ch,start,length).trim();
	}
}
	
	
	