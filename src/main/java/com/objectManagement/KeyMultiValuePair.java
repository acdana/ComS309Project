package com.objectManagement;

import java.sql.Date;

/**
 * This class is used to store our database data before it is sent to the UI.
 * It can contains elements for all necassary attributes in our database.
 * Add more elements as needed.
 * 
 *TODO: Branch out into different key value classes for different tables
 * @deprecated
 * @author Alex
 *
 */
public class KeyMultiValuePair {
	
	private String userName;
	private String password;
	private Date DOB;
	
	public KeyMultiValuePair() {
		this.setUserName(null);
		this.setPassword(null);
		this.setDOB(null);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	
	

}
