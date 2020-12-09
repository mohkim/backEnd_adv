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

import com.kim.advertise.Repository.ContactRepository;
import com.kim.advertise.entity.Contact;
import com.kim.advertise.entity.User;
 

/**
 *
 * @author FREE_MIND
 */

@Repository
public class ContactService {
    
 
	
	@Autowired
	public  UserService userService;
	@Autowired 
	public  ContactRepository contactRepo;
	
	
	public  Contact   save(Contact  contact) {
		 return  contactRepo.save(contact);
             
	}
	public  List<Contact>  getAllContactByUser(User usr){
		return  contactRepo.findAll();
	}
		 
     public void deleteContact(Long id) {
    	 contactRepo.deleteById(id);
     }
     
     public Contact   getbyId(Long  id) {
    	 
    	 Optional<Contact>	salesLoc= contactRepo.findById(id);
    		if(salesLoc.isPresent()) return  salesLoc.get();
    		else  return null;
     }
 	public Contact getByUser(User user) {
		return  contactRepo.getContactByUser(user.getId());
	}
}
