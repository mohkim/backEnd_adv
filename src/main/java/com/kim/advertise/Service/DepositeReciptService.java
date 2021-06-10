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

import com.kim.advertise.Repository.CurrencyRepository;
import com.kim.advertise.Repository.UserDepositeRecieptRepository;
import com.kim.advertise.Repository.UserRepository;
import com.kim.advertise.entity.Currency;
import com.kim.advertise.entity.DepositReciept;
import com.kim.advertise.entity.User;
 

/**
 *
 * @author FREE_MIND
 */

@Repository
public class DepositeReciptService {
    
 
	
	@Autowired
	public  UserService userService;
	@Autowired 
	public  UserDepositeRecieptRepository deposite_service;
	
	
	public  DepositReciept  save(DepositReciept re) {
		
	    return  deposite_service.save(re);
             
	}
		
	public  DepositReciept  getDepositeReciptByReceiptNum(String receipt_No) {
	Optional<DepositReciept>	dep_receipt= deposite_service.findByReceiptNo(receipt_No);
	if(dep_receipt.isPresent()) return  dep_receipt.get();
	else  return null;
		 
	}
	public  DepositReciept  getDepositeRecipt(Long id) {
	Optional<DepositReciept>	dep_receipt= deposite_service.findById(id);
	if(dep_receipt.isPresent()) return  dep_receipt.get();
	else  return null;
		 
	}
 	public  List<DepositReciept>   getAllDepositeReceipt() {
 	return deposite_service.findAll();
 		 
 	}
	public  List<DepositReciept>   getAllDepositeReceiptByUser(Long user_id) {
		return deposite_service.findByUser(user_id);
		 
	}
	
	public void  deleteDepositRecept(Long id) {
		 deposite_service.deleteById(id);
	}

	public List<DepositReciept> getAllDepositeReceiptByCasher(Long user_id) {
		return deposite_service.findByCasher(user_id);
	}
		
 
		 
}
