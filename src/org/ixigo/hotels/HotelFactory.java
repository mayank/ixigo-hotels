package org.ixigo.hotels;

import org.ixigo.hotels.services.APIDataService;
import org.ixigo.hotels.services.DataSourceReaderService;
import org.ixigo.hotels.services.HotelGeneratorService;
import org.ixigo.hotels.services.HotelsListService;
import org.ixigo.hotels.services.MappingFormatterService;

public class HotelFactory {

	private static HotelFactory factory;
	
	private HotelFactory(){
		
	}
	
	public static HotelFactory getInstance(){
		if(factory == null){
			factory = new HotelFactory();
		}
		return factory;
	}
	
	public DataSourceReaderService getDataSourceReaderService(){
		return new DataSourceReaderService();
	}
	
	public HotelGeneratorService getHotelGeneratorService(){
		return new HotelGeneratorService();
	}
	
	public HotelsListService getHotelListService(){
		return new HotelsListService();
	}
	
	public APIDataService getAPIDataService(){
		return new APIDataService();
	}
	
	public MappingFormatterService getMappingFormatterService(){
		return new MappingFormatterService();
	}
}
