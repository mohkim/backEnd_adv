package com.kim.advertise.utility;

import java.time.LocalDate;
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
	  
	  public static String  getReceiptNumber(Long user_id,Long casher_id) {
		  LocalDate tdt=LocalDate.now();
			  
		  String date=tdt.toString().replace(":", "").replace("-", "").replace("T", "").replace(".", "");
		  return  date+"/"+user_id+"/"+casher_id;
	  }
}
