package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Message database table.
 * 
 */
@Entity
@NamedQueries ({
	@NamedQuery(name="getMessages", query="SELECT M.message, M.sender FROM Message M WHERE M.username = :username")
})
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String messageID;

	@Temporal(TemporalType.DATE)
	private Date dateOpened;
	
	@Temporal(TemporalType.DATE)
	private Date dateSent;

	private String username;
	
	private String message;

	private String sender;

	public Message() {
	}

	public String getMessageID() {
		return this.messageID;
	}
	
	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}
	
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDateOpened() {
		return this.dateOpened;
	}

	public void setDateOpened(Date dateOpened) {
		this.dateOpened = dateOpened;
	}

	public Date getDateSent() {
		return this.dateSent;
	}

	public void setDateSent(Date expression) {
		this.dateSent = expression;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSender() {
		return this.sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

}