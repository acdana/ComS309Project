package com.entities;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the Item database table.
 * 
 */
@Entity
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String itemID;

	private String itemName;

	private String saleID;

	private String username;

	public Item() {
	}

	public String getItemID() {
		return this.itemID;
	}

	public void setItemID(String string) {
		this.itemID = string;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getSaleID() {
		return this.saleID;
	}

	public void setSaleID(String saleID) {
		this.saleID = saleID;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}