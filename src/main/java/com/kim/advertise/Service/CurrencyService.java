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
import com.kim.advertise.Repository.UserRepository;
import com.kim.advertise.entity.Currency;
 

/**
 *
 * @author FREE_MIND
 */

@Repository
public class CurrencyService {
    
 
	
	@Autowired
	public  UserRepository userRepo;
	@Autowired 
	public  CurrencyRepository currencyRepo;
	
	
	public  Currency   save(Currency  c) {
		
	    return  currencyRepo.save(c);
             
	}
		 
	public  Currency   getCurrency(Long id) {
	Optional<Currency>	cur= currencyRepo.findById(id);
	if(cur.isPresent()) return  cur.get();
	else  return null;
		 
	}
	public  List<Currency>   getAllCurrency() {
		return currencyRepo.findAll();
		 
	}
	
	public void  deleteCurrency(Long id) {
		 currencyRepo.deleteById(id);
	}
		 
		 
}
