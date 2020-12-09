/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kim.advertise.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kim.advertise.Repository.AddressRepository;
import com.kim.advertise.Repository.UserRepository;
import com.kim.advertise.entity.Address;
import com.kim.advertise.entity.User;
 

/**
 *
 * @author FREE_MIND
 */

@Repository
public class AddressService {
    
 
	
	@Autowired
	public  UserRepository userRepo;
	@Autowired 
	public  AddressRepository adrsRepo;
	
	
	public  Address   save( Address  adrs) {
	   	return  adrsRepo.save(adrs);
	}
		 
	public  Address   getAddress(Long  adr_id) {
		Optional<Address>  adr=adrsRepo.findById(adr_id);
		 if(adr.isPresent()) return  adr.get();
		 else  return  null;
	}
	public  Address  getAddressByUser( User  c) {
	  return  adrsRepo.getAddressByUser(c.getId());
		 
	}
  public  void  deleteById(Long id) {
	  adrsRepo.deleteById(id);
  }
 
		 
		 
}
