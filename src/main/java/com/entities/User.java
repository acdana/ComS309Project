package com.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the User database table.
 * 
 */
@Entity
@NamedQueries({
    @NamedQuery(name="getAllUsernames", query="SELECT U.username FROM User U"),
    @NamedQuery(name="deleteUser", query="DELETE FROM User U WHERE U.username = :usernameToDelete"),
    @NamedQuery(name="penalizeUser", query="UPDATE User U SET U.penalties = U.penalties + 1 WHERE U.username = :usernameToPenalize"),
    @NamedQuery(name="getPenalizedUsers", query="SELECT U.username FROM User U WHERE U.penalties >= 1"),
    @NamedQuery(name="getPenaltyCount", query="SELECT U.penalties FROM User U where U.username = :usernameToCheck"),
    @NamedQuery(name="userLogin", query="SELECT U.username FROM User U where U.username = :username AND U.password = :password")
})

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;

	private String accountStatus;

	private String email;

	private String password;

	private int penalties;

	private String userType;

	public User() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccountStatus() {
		return this.accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPenalties() {
		return this.penalties;
	}

	public void setPenalties(int penalties) {
		this.penalties = penalties;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}