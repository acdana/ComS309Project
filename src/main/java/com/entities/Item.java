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
	private int itemID;

	private String itemName;

	private int saleID;

	private String username;

	public Item() {
		itemID = -1;
		itemName = "";
		saleID = -1;
		username = "";
	}

	public int getItemID() {
		return this.itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getSaleID() {
		return this.saleID;
	}

	public void setSaleID(int saleID) {
		this.saleID = saleID;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}