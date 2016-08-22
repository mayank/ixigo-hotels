/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ixigo.hotels.services;

import java.util.ArrayList;
import java.util.HashMap;

import org.ixigo.hotels.HotelFactory;
import org.ixigo.hotels.models.APIMap;
import org.ixigo.hotels.models.Hotel;
import org.json.simple.JSONObject;

/**
 *
 * @author Vipul
 */
public class HotelsListService {
    
	private DataSourceReaderService reader;
	private APIDataService apiService;
	private HotelGeneratorService hotelGenerator;
	private MappingFormatterService mapper;
    
	// Implementing IoC using DI + Factory Pattern
	public HotelsListService(){
		this.reader = HotelFactory.getInstance().getDataSourceReaderService();
		this.apiService = HotelFactory.getInstance().getAPIDataService();
		this.hotelGenerator = HotelFactory.getInstance().getHotelGeneratorService();
		this.mapper = HotelFactory.getInstance().getMappingFormatterService();
	}
	
	public void createHotelListing(){
		ArrayList<APIMap> mapArray = this.reader.getAllAPIDetails();
		ArrayList <Hotel> hotels = new ArrayList <Hotel>(); 
		
		for(APIMap mapObj: mapArray){
			 hotels.addAll(createHotelObjectFromMap(mapObj));
		}
		
		for(Hotel hotel1: hotels){
			for(Hotel hotel2: hotels){
				if(hotel1.equals(hotel2) && !hotel1.getSource().equals(hotel2.getSource())){
					System.out.print(hotel1.getName()+", "+hotel1.getSource());
					System.out.print(" matches with ");
					System.out.println(hotel1.getName()+", "+hotel2.getSource());
				}
			}
		}
		
	}
	
	private ArrayList <Hotel> createHotelObjectFromMap(APIMap mapObj){
		Object jsonData = this.apiService.fetchDataFromApi(mapObj.getApiUrl());
		ArrayList <Hotel> hotels = new ArrayList <Hotel>();
		if( jsonData instanceof JSONObject ){
			ArrayList <HashMap <String, Object>> mappedArray = this.mapper.createMappedObjects( mapObj.getMapping(), (JSONObject) jsonData );
			for(HashMap<String, Object> hotelMap : mappedArray){
				Hotel hotel = this.hotelGenerator.createHotelFromMap(hotelMap, mapObj.getName());
				hotels.add(hotel);
			}
		}
		return hotels;
	}
}
