/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kim.advertise.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kim.advertise.Repository.ProductCatagoryRepository;
import com.kim.advertise.Repository.ProductSubCatagoryRepository;
import com.kim.advertise.Repository.UserRepository;
import com.kim.advertise.entity.ProductCatagory;
import com.kim.advertise.entity.ProductSubCatagory;
 

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
		 
}
