/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ixigo.hotels.utils;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Vipul
 */
public class JSONReader {
    
	public final static JSONParser parser = new JSONParser();
	
    public static Object fromStringToJSON(String jsonString){
    	try {
			return parser.parse(jsonString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    public static Object fromFileToJSON(String filepath){
    	String filedata = FileReaderUtil.getContents(filepath);
    	if(filedata != null){
    		return fromStringToJSON(filedata);
    	}
    	return null;
    }
	
}
	