
package com.kim.advertise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kim.advertise.Service.CurrencyService;
import com.kim.advertise.Service.ProductCatagoryService;
import com.kim.advertise.Service.ProductSubCatagoryService;
import com.kim.advertise.Service.RoleService;
import com.kim.advertise.Service.SalesLocationService;
import com.kim.advertise.Service.SpecificationHeadOptionService;
import com.kim.advertise.Service.SpecificationHeadService;
import com.kim.advertise.Service.UserService;
import com.kim.advertise.entity.Currency;
import com.kim.advertise.entity.Role;
import com.kim.advertise.entity.SalesLocation;
import com.kim.advertise.entity.SpecificationHead;
import com.kim.advertise.entity.SpecificationHeadOption;
import com.kim.advertise.entity.User;
import com.kim.advertise.entity.post.ERole;

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
		 
	//	jsonToObject.jsonFilesToDatabase();
		 
	}
 
}
