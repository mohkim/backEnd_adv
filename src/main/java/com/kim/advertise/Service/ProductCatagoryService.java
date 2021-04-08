/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kim.advertise.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.kim.advertise.Repository.ProductCatagoryRepository;
import com.kim.advertise.Repository.ProductSubCatagoryRepository;
import com.kim.advertise.Repository.UserRepository;
import com.kim.advertise.controller.user.UserResource;
import com.kim.advertise.entity.ProductCatagory;
import com.kim.advertise.entity.ProductSubCatagory;
import com.kim.advertise.entity.User;
import com.kim.advertise.entity.post.EPostStatus;
import com.kim.advertise.form.CatagoryByQuantity;
import com.kim.advertise.utility.Utility;
 

/**
 *
 * @author FREE_MIND
 */

@Repository
public class ProductCatagoryService {
    
 
	
	@Autowired
	public  UserRepository userRepo;
	@Autowired
	public  ProductCatagoryRepository  proCatRep;
	@Autowired
	public  ProductSubCatagoryRepository  proSubCatRep;
	
	private final Path root = Paths.get("K:\\upload");
	
	public   List<ProductCatagory>   getAllProductCatagory() {
		return  proCatRep.findAll();
	}
	
	public   ProductCatagory    getProductCatagory(Long id) {
         Optional<ProductCatagory>  proCat=proCatRep.findById(id) ;
		if(proCat.isPresent()) return   proCat.get();
		else  return null;

	}
	public   ProductCatagory   save(ProductCatagory proCat) {
     return  proCatRep.save(proCat);
   
	}
	public   boolean    addSubCatagory( Long  cat_id,ProductSubCatagory sub_cat) {
		   ProductCatagory   cat=getProductCatagory(cat_id);
		   if(cat!= null) {
			   cat.addProductSubcatagory(sub_cat);
			   proCatRep.save(cat);	
			   return  true;
		   } 
		   else  return false;
        
	}
	public   boolean    remoSubCatagory( Long  cat_id,ProductSubCatagory sub_cat) {
		   ProductCatagory   cat=getProductCatagory(cat_id);
		   if(cat!= null) {
			   cat.remoProductSubcatagory(sub_cat);
			   proCatRep.save(cat);	
			   return  true;
		   } 
		   else  return false;
     
	}
	public  void  deleteCatagory(Long id) {
	 	proCatRep.deleteById(id);
	}
	
	public boolean saveCatgoryImage(MultipartFile file, ProductCatagory cat) {
		
		try {
		    if(cat.getImg()!=null  ) {
		        Files.deleteIfExists(this.root.resolve(cat.getImg()));
			 } 
	 	    cat.setImg(setCatagoryFileName(file.getOriginalFilename(),cat.getId()));
			Files.copy(file.getInputStream(), this.root.resolve(cat.getImg()));
		 	  
//			String url = MvcUriComponentsBuilder
//			          .fromMethodName(UserResource.class, "getImage", user.getId(),user.getImage_name()).build().toString();
//			
//			log.info("Image upload name => "+url);
//			user.setImage_url(url);
          	save(cat);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
 //           throw new RuntimeException("Could not store the file. Error: " + e.getMessage());

		}
	}
	public String setCatagoryFileName(String file, Long cat_id) {
		String name = Utility.getStringDateTimeofNow() + "_" + cat_id + "_." + Utility.getExtenstion(file);
		return name;
	}
	
	/*
	 * Get all CAtagory  the number  of posts available 
	 * 
	 * */
	public  List<CatagoryByQuantity>  getCatagoryByPostQuantity(){
		
		return  proCatRep.getAllCatagoryByQuantity(EPostStatus.ACTIVE);
	}
}
