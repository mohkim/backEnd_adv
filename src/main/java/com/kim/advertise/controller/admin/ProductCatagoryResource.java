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

import com.kim.advertise.Service.ProductCatagoryService;
import com.kim.advertise.entity.ProductCatagory;
import com.kim.advertise.jwt.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/adv/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ProductCatagoryResource {

	@Autowired
	private ProductCatagoryService catService;

	@GetMapping("/catagory")
	public List<ProductCatagory> getAllCatagory() {
		List<ProductCatagory> list = catService.getAllProductCatagory();
		return list;
	}

	@GetMapping("/catagory/{id}")
	public ProductCatagory getSingleCatagory(@PathVariable Long id) {
		ProductCatagory c = catService.getProductCatagory(id);
		return c;
	}

	@PostMapping("/catagory")
	public ResponseEntity<?> saveNewUser(@Valid @RequestBody ProductCatagory c) {
		if (catService.save(c) != null) {
			return ResponseEntity.ok(new MessageResponse("New Catagory Saved Successfully !!!"));
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse(" Catagory save failed !!!"));
		}

	}

	@DeleteMapping("/catagory/{id}")
	public ResponseEntity<?> saveUpdateUser(@PathVariable Long id) {
		ProductCatagory pc = catService.getProductCatagory(id);

		if (pc != null) {
			if (pc.getProductSubcatagory().size() > 0) {
				return ResponseEntity.badRequest().body(new MessageResponse("Cannot Delete this Catagory !!"));
			} else {
				catService.deleteCatagory(id);
				return ResponseEntity.ok(new MessageResponse("Catagory deleted Successfully !!!"));
			}

		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("Catagory not Found !!!"));
		}

	}
}
