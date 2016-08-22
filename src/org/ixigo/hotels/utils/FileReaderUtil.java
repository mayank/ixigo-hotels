/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ixigo.hotels.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Vipul
 */
public class FileReaderUtil {
    
	// reads file from path and returns its contents as string
	// returns null if file not found or not readable 
    public static String getContents(String filepath){
        String output = null;
        try{
            FileReader file = new FileReader(filepath);
            BufferedReader buffer = new BufferedReader(file);
            StringBuilder outputBuilder = new StringBuilder();
            String line = buffer.readLine();
            while(line != null){
                outputBuilder.append(line);
                outputBuilder.append(System.lineSeparator());
                line = buffer.readLine();
            }
            buffer.close();
            output = outputBuilder.toString();
        } catch( FileNotFoundException e ){
        }catch( IOException e ){
        }
        return output;
    }
    
}
