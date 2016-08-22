/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ixigo.hotels.services;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Vipul
 */
public class MappingFormatterService {
	
	// if format is given return a hashmap, recursively
	// else if source is given move recursively
	// else if string is found, return the same data as it is
	private Object fetchFromSource(Object data, Object selector){
		if(data instanceof String){
			return data;
		}
		JSONObject dataJson = (JSONObject) data;
		if(selector instanceof String){
			return dataJson.get(selector);
		} else if( selector instanceof JSONObject ){
			JSONObject selectorObj = (JSONObject) selector;
			Object[] keys = selectorObj.keySet().toArray();
			
			for(Object key:keys){
				Object selKey = selectorObj.get(key);
				if(key.equals("format")) continue;
				if(key.equals("source"))return fetchFromSource(dataJson, selKey);
				return fetchFromSource(dataJson.get(key), selKey);
			}
		}
		return null;
	}
    
	public ArrayList <HashMap <String, Object>> createMappedObjects(JSONObject mapping, JSONObject data){
		
		JSONObject results = (JSONObject) mapping.get("results");
		JSONObject format = (JSONObject) results.get("format");
		
		Object[] keys = format.keySet().toArray();
		
		JSONArray hotelList = (JSONArray) fetchFromSource(data, results); 
		ArrayList <HashMap <String, Object>> hotels = new ArrayList<HashMap <String, Object>>();
		
		for(Object hotelJSON: hotelList){
			JSONObject hotel = (JSONObject) hotelJSON;
			HashMap<String, Object> hotelMap = new HashMap<String, Object>();
			
			for(Object key: keys){
					Object value = fetchFromSource(hotel, format.get(key));
					hotelMap.put(key.toString(), value);
			}
			hotels.add(hotelMap);
		}
		
		return hotels;
	}
	
}
