package com.objectManagement;

import java.sql.Date;

public class MessageKeyMultiValue {
	
	private String message;
	private String sender;
	private String username;
	private Date dateSent;
	private Date dateOpened;
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getSender() {
		return sender;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Date getDateSent() {
		return dateSent;
	}
	
	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}
	
	public Date getDateOpened() {
		return dateOpened;
	}
	
	public void setDateOpened(Date dateOpened) {
		this.dateOpened = dateOpened;
	}
	
	@Override
	public String toString() {
		return "Username: " + username + "     Sender: " + sender + "     Message: " + message + "     Date Sent: " + dateSent.toString() + "     Date Opened: " + dateOpened.toString();
	}
	
}
