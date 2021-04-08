package com.kim.advertise.controller.user;

 

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kim.advertise.Service.AddressService;
import com.kim.advertise.Service.UserService;
import com.kim.advertise.entity.Address;
import com.kim.advertise.entity.User;
import com.kim.advertise.jwt.MessageResponse;

 
@RestController
@RequestMapping("/adv")

public class AddressController {

	@Autowired
	private AddressService addressServ;

	@Autowired
	private UserService userServ;
	
	private Logger  log=LoggerFactory.getLogger(this.getClass());

	@GetMapping("/user/{id}/address")
	public  Address getAddressByUser(@PathVariable Long id) {
		User user = userServ.getUser(id);

		if (user == null) {
			return null;
		}

		return addressServ.getAddressByUser(user);
	}

	@PostMapping("/user/{id}/address")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> saveNewAddress(@Valid @RequestBody Address c, @PathVariable Long id) {
   
		log.info("address data =>"+c.toString());
		log.info("user id =>"+id);
		 
		User user = userServ.getUser(id);

		if (user == null) {
			return ResponseEntity.ok(new MessageResponse("User Not Found  !!!"));
		}

		c.setUser(user);

		if (addressServ.save(c) != null) {
			return ResponseEntity.ok(new MessageResponse("Address Saved Successfully !!!"));
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse(" Address Save failed !!!"));
		}

	}

	@DeleteMapping("/user/address/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
		addressServ.deleteById(id);

		return ResponseEntity.ok(new MessageResponse("Address deleted Successfully !!!"));

	}
}
