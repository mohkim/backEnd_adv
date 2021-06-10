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

import com.kim.advertise.Repository.ProductSubCatagoryRepository;
import com.kim.advertise.entity.ProductSubCatagory;
import com.kim.advertise.entity.emum.EPostStatus;
import com.kim.advertise.form.CatagoryByQuantity;
import com.kim.advertise.form.SubCatagoryByQuantity;
 

/**
 *
 * @author FREE_MIND
 */

@Repository
public class ProductSubCatagoryService {
    

	
	 
	@Autowired
	public  ProductCatagoryService  proCatService;
	@Autowired
	public  ProductSubCatagoryRepository  proSubCatRep;
	 
	
	public   ProductSubCatagory    getProductSubCatagory(Long id) {
       
		Optional<ProductSubCatagory>  prosubCat=proSubCatRep.findById(id) ;
		if(prosubCat.isPresent()) return   prosubCat.get();
		else  return null;

	}
	
	public  ProductSubCatagory  save(ProductSubCatagory proSubCat ) {
           return  proSubCatRep.save(proSubCat);
     
	}
	 
	public   void   remoSubCatagory( Long id) {
		   
        proSubCatRep.deleteById(id);
	}
	
	/*
	 * Get all CAtagory  the number  of posts available 
	 * 
	 * */
	public  List<SubCatagoryByQuantity>  getSubCatagoryByPostQuantity(Long id){
		
		return  proSubCatRep.getAllSubCatagoryByQuantity(EPostStatus.ACTIVE,id);
	}
		 
}
