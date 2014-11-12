package com.objectManagement;

import java.awt.geom.Point2D;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This is the object mapping class for raw data retrieved from the database.
 * We process the raw data into meaningful types here.
 * @author Alex
 *
 */
public class ObjectMapper {
	
	/**
	 * This method is used to process a raw list of objects into a valid
	 * JSON string for the frontend.
	 * 
	 * @param rawUsernames	The list of raw usernames that we wish to process.
	 * @return An array list of strings representing the user names retrieved.
	 */
	public static String mapUsernames(List<Object> rawUsernames) {
		
		String namesToReturn = "{\"Result\":[{\"Status\": \"Success\"}, {\"Usernames\":[";
		for(Object name : rawUsernames) {
			String tempName = "{\"Username\":\"" + (String) name + "\"},";
			namesToReturn += tempName;
		}
		if(rawUsernames.size() > 0) {
		namesToReturn = namesToReturn.substring(0, namesToReturn.length()-1);
		}
		namesToReturn += "]}]}";
		return namesToReturn;
		
	}
	
	
	/**
	 * This method is used to translate raw message data into valid
	 * JSON strings for the frontend.
	 * 
	 * @param rawMessages The raw message objects to be translated.
	 * @return An array list of MessageKeyMultiValue objects representing messages.
	 */
	public static String mapMessages(List<Object[]> rawMessages) {
		
		String allMessages = "{\"Result\":[{\"Status\":\"Success\"}, {\"Messages\":[";
		for(Object[] rawMessage : rawMessages) {
			String content = (String) rawMessage[0]; 
			String name = (String) rawMessage[1];
			String tempMessage = "{\"Sender\":\"" + name + "\",\"Message\":\"" + content + "\"},";
			allMessages += tempMessage;
		}
		if(rawMessages.size() > 0) {
			allMessages = allMessages.substring(0, allMessages.length()-1);
		}
		allMessages += "]}]}";
		
		return allMessages;
	}
	
	/**
	 * This method is used to translate raw sale location coordinates into
	 * readable Point2D coordinates for us to analyze.
	 * 
	 * @param rawCoordinates
	 * @return an ArrayList of Point2D.Double coordinates
	 */
	public static ArrayList<Point2D.Double> mapCoordinates(List<Object[]> rawCoordinates) {
		
		ArrayList<Point2D.Double> coordinateList = new ArrayList<Point2D.Double>();
		
		for(Object[] rawCoordinate : rawCoordinates) {
			Point2D.Double coordinate = new Point2D.Double();
			coordinate.setLocation((Double) rawCoordinate[0], (Double) rawCoordinate[1]);
			coordinateList.add(coordinate);
		
		}
		return coordinateList;
	}
	
	public static String mapCurrentSales(List<Object[]> rawSales) {
		String currentSales = "{\"Result\":[{\"Status\":\"Success\"}, {\"Sales\":[";
		
		for(Object[] rawSale : rawSales) {
			String saleDescription = (String) rawSale[0];
			String seller = (String) rawSale[1]; 
			Date dateCreated = (Date) rawSale[2];
			String dateString = new SimpleDateFormat("dd/MM/yyyy").format(dateCreated);
			String tempSale = "{\"saleDescription\":\"" + saleDescription + "\",\"Seller\":\"" + seller + "\",\"dateCreated\":\"" + dateString + "\"},";
			currentSales += tempSale;
		}
		if(rawSales.size() > 0) {
			currentSales = currentSales.substring(0, currentSales.length()-1);
		}
		currentSales += "]}]}";
		
		return currentSales;
		
	}
	
}
