package com.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@NamedQuery(name="getAllUsernames", query="SELECT U.username FROM User U")

public class User implements Serializable {
	
	   
	@Id
	private String username;
	
	private String password;
	private String profilePicture;
	private Integer reputation;
	private String bio;
	private String userType;
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}   
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}   
	public String getProfilePicture() {
		return this.profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}   
	public Integer getReputation() {
		return this.reputation;
	}

	public void setReputation(Integer reputation) {
		this.reputation = reputation;
	}   
	public String getBio() {
		return this.bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}   
	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
   
}
