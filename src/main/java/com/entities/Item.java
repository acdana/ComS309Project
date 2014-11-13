package com.entities;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the Item database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i"),
	@NamedQuery(name="getUsersItems", query="SELECT i FROM Item i WHERE i.username = :username"),
})
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int itemID;

	private String itemName;

	private int saleID;

	private String username;

	public Item() {
	}

	public int getItemID() {
		return this.itemID;
	}

	public void setItemID(int ID) {
		this.itemID = ID;
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

	public void setSaleID(int ID) {
		this.saleID = ID;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String toString() {
		
		return "{\"Item\":[\"itemID\":\"" + itemID + "\", \"SaleID\":\"" + saleID + "\", \"itemName\":\"" + itemName + "\", \"username\":\"" + username + "\"]}";
		
	}

}