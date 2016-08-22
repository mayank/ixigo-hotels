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
    
	public Hotel createHotelFromMap(HashMap<String, Object> hotelMap){
		Hotel hotel = new Hotel();
		hotel.setName((String)hotelMap.get("name"));
		
		Rating rating = new Rating();
		rating.setAvg(Double.parseDouble(hotelMap.get("rating").toString()));
		rating.setTotalUsers(Long.parseLong(hotelMap.get("users").toString()));
		hotel.setRating(rating);
		
		Address address = new Address();
		address.setLatitude(Double.parseDouble(hotelMap.get("latitude").toString()));
		address.setLongitude(Double.parseDouble(hotelMap.get("longitude").toString()));
		address.setTextAddress((String) hotelMap.get("place"));
		hotel.setAddress(address);
		
		return hotel;
	}
}
