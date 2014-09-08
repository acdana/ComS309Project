package com.db;
 
import java.util.ArrayList;
import java.util.List;
import com.model.BasicUser;
import com.model.SellerGroup;
 
public class DB {

	static List<BasicUser> db = new ArrayList<BasicUser>();
	static {
		BasicUser user1 = new BasicUser();
		BasicUser user2 = new BasicUser();
		BasicUser user3 = new BasicUser();
		
		user1.setUserName("Alex");
		user1.setPassword("AlexPassword");
		
		user2.setUserName("John");
		user2.setPassword("myPassword");
		
		user3.setUserName("Bill");
		user3.setPassword("hello");
		
		SellerGroup group1 = new SellerGroup();
		group1.setGroupId("Electronics");
		group1.setGroupLocation("West Ames");
		
		user1.setSellerGroup(group1);
		user2.setSellerGroup(group1);
		user3.setSellerGroup(group1);
		
		db.add(user1);
		db.add(user2);
		db.add(user3);
	}
 
	public static String getBasicUser(String name) {
		String userInfo = "";
		for(BasicUser b : db) {
			if(b.getUserName().equals(name)) {
				userInfo = name + " - " + b.getSellerGroup().getGroupId();
			}
		}
		return userInfo;
	}
 
	public static String getAllBasicUsers() {
		String userNames = "--";
		for(BasicUser b : db) {
			userNames += b.getUserName() + "--";
		}
		return userNames;
	}
}