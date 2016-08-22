/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ixigo.hotels.models;

import org.ixigo.hotels.utils.CommonUtil;

/**
 *
 * @author Vipul
 */
public class Address {
    
    double latitude;
    double longitude;
    
    String textAddress;	
    String city;
    String state;
    
    int pincode;

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

	public String getTextAddress() {
		return textAddress;
	}

	public void setTextAddress(String textAddress) {
		this.textAddress = textAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	// generating same hash code for similar address
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(Math.round(latitude*1000.0)/1000.0);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(Math.round(longitude*1000.0)/1000.0);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if( latitude == 0 || longitude == 0 || other.latitude == 0 || other.longitude == 0 ){
			return false;
		}
		if( CommonUtil.getDistanceFromLatLonInKm(latitude,longitude,other.latitude,other.longitude) > 0.12 ){
			return false;
		}
		if (textAddress == null) {
			if (other.textAddress != null)
				return false;
		}  else if( !checkAddressAlgo(textAddress.toLowerCase(), other.textAddress.toLowerCase()) )
			return false;
		return true;
	}
	
	private boolean checkAddressAlgo(String textAddress, String otherTextAddress){
		int len = textAddress.length();
		int otherLen = otherTextAddress.length();
		if( len < otherLen/2 && otherTextAddress.indexOf(textAddress) < 0 )
			return false;
		if( otherLen < len/2 && textAddress.indexOf(otherTextAddress) < 0 )
			return false;
		if( CommonUtil.levenshteinDistance(textAddress, otherTextAddress) > 20 )
			return false;
		return true;
	}
	
    
}
