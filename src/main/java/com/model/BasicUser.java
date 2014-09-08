package com.model;

public class BasicUser {
	
	private String userName;
	private String password;
	private SellerGroup sellerGroup;
	
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

	public SellerGroup getSellerGroup() {
		return sellerGroup;
	}

	public void setSellerGroup(SellerGroup sellerGroup) {
		this.sellerGroup = sellerGroup;
	}
	

}
