/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kim.advertise.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kim.advertise.Repository.SpecificationHeadOptionRepository;
import com.kim.advertise.entity.SpecificationHeadOption;

 
 

/**
 *
 * @author FREE_MIND
 */

@Repository
public class SpecificationHeadOptionService {
    

	
	 
 
	@Autowired
	public  SpecificationHeadOptionRepository   speHedOptionRepo;
	 
	
	public   SpecificationHeadOption    getSpecificationHeadOption (Long id) {
       
		Optional<SpecificationHeadOption >  prosubCat=speHedOptionRepo.findById(id) ;
		if(prosubCat.isPresent()) return   prosubCat.get();
		else  return null;

	}
	
	public  SpecificationHeadOption   save(SpecificationHeadOption  specificationHead ) {
           return  speHedOptionRepo.save(specificationHead);
     
	}
	 
	public  void    removeSpecificationHeadOption( Long  id ) {
		speHedOptionRepo.deleteById(id);     
	}
	
		 
}
