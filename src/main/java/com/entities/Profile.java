package com.entities;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the Profile database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Profile.findAll", query="SELECT p FROM Profile p"),
	@NamedQuery(name="getProfPic", query="SELECT p.profilePicture FROM Profile p where p.username=:username"),
	@NamedQuery(name="getBio", query="SELECT p.bio FROM Profile p where p.username=:username")
})
public class Profile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;

	private String bio;

	private String profilePicture;

	private int reputation;

	public Profile() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBio() {
		return this.bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getProfilePicture() {
		return this.profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public int getReputation() {
		return this.reputation;
	}

	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

}