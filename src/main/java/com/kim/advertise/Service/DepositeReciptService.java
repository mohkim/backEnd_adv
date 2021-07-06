/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kim.advertise.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.kim.advertise.Repository.DepositeRecieptRepository;
import com.kim.advertise.entity.DepositReciept;
 

/**
 *
 * @author FREE_MIND
 */

@Repository
public class DepositeReciptService {
    
 
	
	@Autowired
	public  UserService userService;
	@Autowired 
	public  DepositeRecieptRepository deposite_repo;
	
	
	public  DepositReciept  save(DepositReciept re) {
		
	    return  deposite_repo.save(re);
             
	}
		
 
	public  DepositReciept  getDepositeRecipt(Long id) {
	Optional<DepositReciept>	dep_receipt= deposite_repo.findById(id);
	if(dep_receipt.isPresent()) return  dep_receipt.get();
	else  return null;
		 
	}
 	public  List<DepositReciept>   getAllDepositeReceipt() {
 	return deposite_repo.findAll();
 		 
 	}
	public  List<DepositReciept>   getAllDepositeReceiptByUser(Long user_id) {
		return deposite_repo.findByUser(user_id);
		 
	}
	
	public void  deleteDepositRecept(Long id) {
		 deposite_repo.deleteById(id);
	}

	public List<DepositReciept> getAllDepositeReceiptByCasher(Long user_id) {
		return deposite_repo.findByCasher(user_id);
	}
	
	public List<DepositReciept> getAllDepositeReceiptByCasherByDate(Long user_id,LocalDateTime start,LocalDateTime end) {
		return deposite_repo.findByCasherByDate(user_id, start, end);
	}
	
		
 
		 
}
