package com.kim.advertise.controller.admin;

import java.util.List;

import javax.validation.Valid;

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

import com.kim.advertise.Service.SalesLocationService;
import com.kim.advertise.entity.SalesLocation;
import com.kim.advertise.jwt.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/adv/admin")

public class SalesLocationResource {

	@Autowired
	private SalesLocationService salesLocServ;

	@GetMapping("/saleslocation")
	public List<SalesLocation> getAllSalesLocation() {
		List<SalesLocation> list = salesLocServ.getAllLocation();
		return list;
	}

	@GetMapping("/saleslocation/{id}")
	public SalesLocation getSingleSalesLocation(@PathVariable Long id) {
		SalesLocation c = salesLocServ.getbyId(id);
		return c;
	}

	@PostMapping("/saleslocation")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> saveNewSalesLocation(@Valid @RequestBody SalesLocation c) {
		if (salesLocServ.save(c) != null) {
			return ResponseEntity.ok(new MessageResponse("New Catagory Saved Successfully !!!"));
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse(" Catagory save failed !!!"));
		}

	}

	@DeleteMapping("/saleslocation/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteSalesLocation(@PathVariable Long id) {
		salesLocServ.deleteSalesLocation(id);
			   
		 return ResponseEntity.ok(new MessageResponse("Location deleted Successfully !!!"));
	

	}
}
