package com.resource;

import java.util.ArrayList;
import java.util.List;

public class ObjectMapper {
	
	public static ArrayList<String> mapAllUsernames(List<Object> rawUsernames) {
		
		ArrayList<String> usernames = new ArrayList<String>();
		for(Object rawName : rawUsernames) {
			usernames.add((String) rawName);
		}
		return usernames;
	}
	
	
}
