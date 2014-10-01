package com.objectManagement;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the object mapping class for raw data retrieved from the database.
 * We process the raw data into meaningful types here.
 * @author Alex
 *
 */
public class ObjectMapper {
	
	/**
	 * This method is used to process a raw list of objects into a list of strings
	 * representing user names.
	 * 
	 * @param rawUsernames	The list of raw usernames that we wish to process.
	 * @return An array list of strings representing the user names retrieved.
	 */
	public static ArrayList<String> mapUsernames(List<Object> rawUsernames) {
		
		ArrayList<String> usernames = new ArrayList<String>();
		for(Object rawName : rawUsernames) {
			usernames.add((String) rawName);
		}
		return usernames;
		
	}
	
	
	/**
	 * This method is used to translate raw message data into readable
	 * MessageKeyMultiValue objects.
	 * 
	 * @param rawMessages The raw message objects to be translated.
	 * @return An array list of MessageKeyMultiValue objects representing messages.
	 */
	public static ArrayList<MessageKeyMultiValue> mapMessages(List<Object[]> rawMessages) {
		
		ArrayList<MessageKeyMultiValue> allMessages = new ArrayList<MessageKeyMultiValue>();
		for(Object[] rawMessage : rawMessages) {	
			
			MessageKeyMultiValue message = new MessageKeyMultiValue();
			message.setUsername("Test");
			message.setMessage((String) rawMessage[0]);
			message.setSender((String) rawMessage[1]); 
			allMessages.add(message);
		}
		
		return allMessages;
		
	}
	
}
