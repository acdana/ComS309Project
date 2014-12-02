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
	@NamedQuery(name="getUsersItems", query="SELECT i FROM Item i WHERE i.username = :username")
})
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

	public void setItemID(String ID) {
		this.itemID = ID;
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

	public void setSaleID(String saleID2) {
		this.saleID = saleID2;
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