package com.kim.advertise;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 // import  static  com.kim.advertise.utility.TimeDateUtility.getStringDateTimeofNow;;
public class demo {

	public static void main(String[] args) {
	  Path root = Paths.get("E:\\upload");
	 
	  System.out.println( root.resolve("dkjlsdjfklsd.jpg").toString());
	 
	   
	}
	public static void  bycriptysample() {
		 BCryptPasswordEncoder  endoer=new  BCryptPasswordEncoder();
		  
		    boolean  f1=endoer.matches("ll123456789", "$2a$10$Wry6C5Pb6d6qNs5XJkC95OSbALrqoGgOHTpOJpPowO69hLhKNzaTy");
		    
		    if(f1) {
		    	System.out.println("password Matched !!!!");
		    }else {
		    	System.out.println("password do not Match !!! ");
		    }
	}

  
}
