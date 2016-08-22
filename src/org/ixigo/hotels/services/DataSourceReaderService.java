/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ixigo.hotels.services;

import java.util.ArrayList;

import org.ixigo.hotels.models.APIMap;
import org.ixigo.hotels.utils.JSONReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Vipul
 */
public class DataSourceReaderService {
    
    private final String mappingFile = "data/mapping.json";
    
    public ArrayList<APIMap> getAllAPIDetails(){
    	Object obj = JSONReader.fromFileToJSON(this.mappingFile);
    	if( obj instanceof JSONObject){
    		Object mapArrObj = ((JSONObject) obj).get("data");
    		if(mapArrObj instanceof JSONArray ){
    			JSONArray mapArray = (JSONArray) mapArrObj;
    			ArrayList<APIMap> apiMapsArray = new ArrayList<APIMap>();
    	    	if(mapArray != null && mapArray.size() > 0){
    	    		for(Object mapObj: mapArray){
    	    			JSONObject mapJsonObj = (JSONObject) mapObj;
    	        		apiMapsArray.add(this.createAPIMapFromJSON(mapJsonObj));
    	        	}
    	    	}
    	    	return apiMapsArray;
    		}
    	}
		return null;
    }
    
    private APIMap createAPIMapFromJSON(JSONObject mapObj){
    	APIMap map = new APIMap();
    	map.setApiUrl(mapObj.get("API").toString());
    	map.setMapping((JSONObject) mapObj.get("mapping"));
    	return map;
    }

}
