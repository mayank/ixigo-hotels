/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ixigo.hotels.services;

import org.ixigo.hotels.utils.JSONReader;

/**
 *
 * @author Vipul
 */
public class APIDataService {
    
	public Object fetchDataFromApi(String APIUrl){
		// hack, reading from pre-fetched API data files
		// alternatively some API calls must be added here
		return JSONReader.fromFileToJSON(APIUrl);
	}
	
}
