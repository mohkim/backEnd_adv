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

import com.kim.advertise.Repository.SpecificationHeadRepository;
import com.kim.advertise.entity.SpecificationHead;

 
 

/**
 *
 * @author FREE_MIND
 */

@Repository
public class SpecificationHeadService {
    

	
	 
	@Autowired
	public  ProductSubCatagoryService  proSubCatService;
	@Autowired
	public  SpecificationHeadRepository   speHedRepo;
	 
	
	public   SpecificationHead    getSpecificationHead (Long id) {
       
		Optional<SpecificationHead >  prosubCat=speHedRepo.findById(id) ;
		if(prosubCat.isPresent()) return   prosubCat.get();
		else  return null;

	}
	
	public  SpecificationHead   save(SpecificationHead  specificationHead ) {
	
           return  speHedRepo.save(specificationHead);
     
	}
	 
	public  void    removeSpecificationHead( Long  id ) {
		speHedRepo.deleteById(id);     
	}

	 
	
		 
}
