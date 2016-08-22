package org.ixigo.hotels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.ixigo.hotels.models.APIMap;
import org.ixigo.hotels.models.Hotel;
import org.ixigo.hotels.services.APIDataService;
import org.ixigo.hotels.services.DataSourceReaderService;
import org.ixigo.hotels.services.HotelGeneratorService;
import org.ixigo.hotels.services.MappingFormatterService;
import org.json.simple.JSONObject;

public class HotelListing {

	public static void main(String[] args) {
		
		new HotelListing().createHotelListing();
		
	}
	
	private DataSourceReaderService reader;
	private APIDataService apiService;
	private HotelGeneratorService hotelGenerator;
	private MappingFormatterService mapper;
	
	public HotelListing(){
		this.reader = new DataSourceReaderService();
		this.apiService = new APIDataService();
		this.hotelGenerator = new HotelGeneratorService();
		this.mapper = new MappingFormatterService();
	}
	
	public void createHotelListing(){
		ArrayList<APIMap> mapArray = this.reader.getAllAPIDetails();
		HashSet<Hotel> hotelList = new HashSet<Hotel>(); 
		
		for(APIMap mapObj: mapArray){
			createHotelObjectFromMap(mapObj);
		}
	}

	private void createHotelObjectFromMap(APIMap mapObj){
		Object jsonData = this.apiService.fetchDataFromApi(mapObj.getApiUrl());
		//System.out.println(jsonData.toString());
		if( jsonData instanceof JSONObject ){
			ArrayList <HashMap <String, Object>> mappedArray = this.mapper.createMappedObjects( mapObj.getMapping(), (JSONObject) jsonData );
			//System.out.println(mappedArray.toJSONString());
			for(HashMap<String, Object> hotelMap : mappedArray){
				Hotel hotel = this.hotelGenerator.createHotelFromMap(hotelMap);
				System.out.println(hotel.toString());
			}
		}
		//return this.hotelGenerator.createHotelFromMappedData(mappedArray);
	}
}
