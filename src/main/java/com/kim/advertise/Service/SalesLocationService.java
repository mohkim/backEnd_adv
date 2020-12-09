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

import com.kim.advertise.Repository.SalesLocationRepository;
import com.kim.advertise.Repository.UserRepository;
import com.kim.advertise.entity.SalesLocation;
 

/**
 *
 * @author FREE_MIND
 */

@Repository
public class SalesLocationService {
    
 
	
	@Autowired
	public  UserRepository userRepo;
	@Autowired 
	public  SalesLocationRepository salesLocRepo;
	
	
	public  SalesLocation   save(SalesLocation  salesLocation) {
		 return  salesLocRepo.save(salesLocation);
             
	}
	public  List<SalesLocation>  getAllLocation(){
		return  salesLocRepo.findAll();
	}
		 
     public void deleteSalesLocation(Long id) {
    	 salesLocRepo.deleteById(id);
     }
     
     public SalesLocation   getbyId(Long  id) {
    	 
    	 Optional<SalesLocation>	salesLoc= salesLocRepo.findById(id);
    		if(salesLoc.isPresent()) return  salesLoc.get();
    		else  return null;
     }
		 
}
