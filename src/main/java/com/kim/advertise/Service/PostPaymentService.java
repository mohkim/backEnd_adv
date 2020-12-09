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

import com.kim.advertise.Repository.PostPaymentRepository;
import com.kim.advertise.entity.PostPayment;
 

/**
 *
 * @author FREE_MIND
 */

@Repository
public class PostPaymentService {
  
	@Autowired 
	public  PostPaymentRepository postPayRepo;
	
	
	public  PostPayment   save(PostPayment  postPayation) {
		 return  postPayRepo.save(postPayation);
             
	}
	public  List<PostPayment>  getAllPostPayment(){
		return  postPayRepo.findAll();
	}
		 
     public void deletePostPayment(Long id) {
    	 postPayRepo.deleteById(id);
     }
     
     public PostPayment   getbyId(Long  id) {
    	 
    	 Optional<PostPayment>	postPay= postPayRepo.findById(id);
    		if(postPay.isPresent()) return  postPay.get();
    		else  return null;
     }
		 
}
