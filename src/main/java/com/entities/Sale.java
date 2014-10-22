package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the Sale database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "getAllCoordinates", query = "SELECT S.longitude, S.latitude FROM Sale S"),
	@NamedQuery(name = "getTotTrades", query = "SELECT count(s.primarySeller) from Sale s where s.primarySeller = :username")
})
public class Sale implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int saleID;

	private String chosenLocation;
	
	private double latitude;
	
	private double longitude;

	@Temporal(TemporalType.DATE)
	private Date dateCreated;

	private String primarySeller;

	private String primarySellerLocation;

	private String secondarySeller;

	private String secondarySellerLocation;

	public Sale() {
	}

	public int getSaleID() {
		return this.saleID;
	}

	public void setSaleID(int saleID) {
		this.saleID = saleID;
	}

	public String getChosenLocation() {
		return this.chosenLocation;
	}

	public void setChosenLocation(String chosenLocation) {
		this.chosenLocation = chosenLocation;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getPrimarySeller() {
		return this.primarySeller;
	}

	public void setPrimarySeller(String primarySeller) {
		this.primarySeller = primarySeller;
	}

	public String getPrimarySellerLocation() {
		return this.primarySellerLocation;
	}

	public void setPrimarySellerLocation(String primarySellerLocation) {
		this.primarySellerLocation = primarySellerLocation;
	}

	public String getSecondarySeller() {
		return this.secondarySeller;
	}

	public void setSecondarySeller(String secondarySeller) {
		this.secondarySeller = secondarySeller;
	}

	public String getSecondarySellerLocation() {
		return this.secondarySellerLocation;
	}

	public void setSecondarySellerLocation(String secondarySellerLocation) {
		this.secondarySellerLocation = secondarySellerLocation;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}