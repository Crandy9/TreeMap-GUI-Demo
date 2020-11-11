/*
 * File: StateChangeable.java
 * Purpose: manage real estate database using GUI
 */

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Property implements StateChangeable {
	
	
	private String address;
	private int bedrooms;
	private int squareFeet;
	private int price;
	
	//declare enum class
	public static Status propStatus;
	
	enum Status {
		FOR_SALE, UNDER_CONTRACT, SOLD;
	};
	
	
	
	
	public Property (String address, int bedrooms, int squareFeet, int price , Status propStatus) {
		this.address = address;
		this.bedrooms = bedrooms;
		this.squareFeet = squareFeet;
		this.price = price;
		this.propStatus = propStatus;
		changeState(propStatus);
	}
	
	
	
	DecimalFormat df = new DecimalFormat("#,###.00");
	
	//returns string of address, bedrooms, square feet, price and property status
	public String toString () {
		return "Property Address: " + address + "\nNo. of bedrooms: " + bedrooms + "\nSquare ft. : " + NumberFormat.getNumberInstance(Locale.US).format(squareFeet) + "\nPrice: $" + df.format(price) + 
				"\nStatus: " + propStatus; 
		
	}

	@Override
	public Enum changeState(Enum t) {
	return t;
	}
}
