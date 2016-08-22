/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ixigo.hotels.services;

import java.util.HashMap;

import org.ixigo.hotels.models.Address;
import org.ixigo.hotels.models.Hotel;
import org.ixigo.hotels.models.Rating;

/**
 *
 * @author Vipul
 */
public class HotelGeneratorService {
    
	private double objectToDouble(Object obj){
		double data = 0;
		try{
			if(obj != null){
				String str = obj.toString();
				if(str != ""){
					data = Double.parseDouble(str);
				}
			}
		}catch(NumberFormatException e){
			
		}
		return data;
	}
	private long objectToLong(Object obj){
		long data = 0;
		try{
			if(obj != null){
				String str = obj.toString();
				if(str != ""){
					data = Long.parseLong(str);
				}
			}
		}catch(NumberFormatException e){
			
		}
		return data;
	}
	
	public Hotel createHotelFromMap(HashMap<String, Object> hotelMap, String source){
		
		Hotel hotel = new Hotel();
		hotel.setName((String)hotelMap.get("name"));
		
		Rating rating = new Rating();
		
		rating.setAvg(objectToDouble(hotelMap.get("rating")));
		rating.setTotalUsers(objectToLong(hotelMap.get("users")));
		hotel.setRating(rating);
		
		Address address = new Address();
		address.setLatitude(objectToDouble(hotelMap.get("latitude")));
		address.setLongitude(objectToDouble(hotelMap.get("longitude")));
		address.setTextAddress((String) hotelMap.get("place"));
		hotel.setAddress(address);
		
		hotel.setSource(source);
		
		return hotel;
	}

}
