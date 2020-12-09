package com.kim.advertise.utility;

import java.time.LocalDateTime;

public class Utility {

	public static String  getStringDateTimeofNow() {
		LocalDateTime  tdt=LocalDateTime.now();
		return tdt.toString().replace(":", "").replace("-", "").replace("T", "").replace(".", "");
	}
	  public static  String  getExtenstion(String file) {
	    	String fileName = file;
	        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
	        return fileName.substring(fileName.lastIndexOf(".")+1);
	        else return "";
	    }
}
