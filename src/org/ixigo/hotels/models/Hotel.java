/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ixigo.hotels.models;

import java.util.ArrayList;

import org.ixigo.hotels.utils.CommonUtil;

/**
 *
 * @author Vipul
 */
public class Hotel {
    
    public String name;
    public Address address;
    public ArrayList <Amenity> amenities;
    public ArrayList <Room> rooms;
    public Rating rating;
    public String source;
    
    
    public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ArrayList<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(ArrayList<Amenity> amenities) {
        this.amenities = amenities;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hotel other = (Hotel) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!nameMatchingAlgo(name, other.name))
			return false;
		return true;
	}
	
	// calculating half of object if long name is there
	// else looking for levenshteinsDistance for spelling mistakes
	private boolean nameMatchingAlgo(String name, String otherName){
		int len = name.length();
		int otherLen = otherName.length();
		if( len < otherLen/2 && otherName.indexOf(name) < 0 )
			return false;
		if( otherLen < len/2 && name.indexOf(otherName) < 0 )
			return false;
		if( CommonUtil.levenshteinDistance(name, otherName) > 8 )	// optimum levenshtein distance
			return false;
		return true;
	}
    
    
}
