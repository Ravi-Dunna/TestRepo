package com.Varden.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;



public class TestProperties {
	
/**
 * This method is used to load configs to a properties file    
 */		
public static void main(String[] args) throws IOException {
	
		String path=System.getProperty("user.dir");
		System.out.println(path);
		System.out.println(path+"\\src\\test\\resources\\properties\\config.properties");
		
		Properties config=new Properties();
		Properties OR=new Properties();
		
		FileInputStream fis= new FileInputStream(path+"\\src\\test\\resources\\properties\\config.properties");
		config.load(fis);		
		
		fis= new FileInputStream(path+"\\src\\test\\resources\\properties\\OR.properties");
		OR.load(fis);		
}

}
