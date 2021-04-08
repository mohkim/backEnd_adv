package com.kim.advertise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kim.advertise.Service.ProductCatagoryService;
import com.kim.advertise.form.CatagoryByQuantity;

 
@RestController
@RequestMapping("/adv/")
public class DemoControler {
 
	
	@Autowired
	private  ProductCatagoryService catService;
 



}
