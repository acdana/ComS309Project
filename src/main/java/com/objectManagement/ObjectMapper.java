package com.objectManagement;

import java.util.ArrayList;
import java.util.List;


public class ObjectMapper {
	
	public static ArrayList<String> mapUsernames(List<Object> rawUsernames) {
		
		ArrayList<String> usernames = new ArrayList<String>();
		for(Object rawName : rawUsernames) {
			usernames.add((String) rawName);
		}
		return usernames;
		
	}
	
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
