package com.objectManagement;

import java.sql.Date;

/**
 * This is the key value class for the Message table.
 * We use getters and setters for all variables within
 * a Message.
 * 
 * @author Alex Dana
 */
public class MessageKeyMultiValue {
	
	/**The message contents
	 **/
	private String message;
	
	/**The sender of the message
	 **/
	private String sender;
	
	/**The user who the message is sent to
	 **/
	private String username;
	
	/**The date that the message was sent
	 **/
	private Date dateSent;
	
	/**The date that the message was opened
	 **/
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
