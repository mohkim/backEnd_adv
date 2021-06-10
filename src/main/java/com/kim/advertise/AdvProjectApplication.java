
package com.kim.advertise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdvProjectApplication implements CommandLineRunner {

 
	 
	  @Autowired
	  private DefaultData  data;
	 
	  
	  @Autowired JsonToObject  jsonToObject;

	public static void main(String[] args) {
	 	SpringApplication.run(AdvProjectApplication.class, args);
 }
	

	@Override
	public void run(String... arg) throws Exception {
		data.setData();
		 
	 	jsonToObject.jsonFilesToDatabase();
		 
	}
 
}
