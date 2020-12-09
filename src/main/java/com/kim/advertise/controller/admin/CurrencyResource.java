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

import com.kim.advertise.Service.CurrencyService;
import com.kim.advertise.entity.Currency;
import com.kim.advertise.jwt.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/adv/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class CurrencyResource {

	@Autowired
	private CurrencyService  currService;

	@GetMapping("/currency")
	public List<Currency> getAllCurrency() {
		List<Currency> list = currService.getAllCurrency();
		return list;
	}

	@GetMapping("/currency/{id}")
	public Currency getSingleCurrency(@PathVariable Long id) {
		Currency c = currService.getCurrency(id);
		return c;
	}

	@PostMapping("/currency")
	public ResponseEntity<?> saveNewCurrency(@Valid @RequestBody Currency c) {
		if (currService.save(c) != null) {
			return ResponseEntity.ok(new MessageResponse("New Currency Saved Successfully !!!"));
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse(" Currency save failed !!!"));
		}

	}

	@DeleteMapping("/currency/{id}")
	public ResponseEntity<?> saveUpdateUser(@PathVariable Long id) {
		  currService.deleteCurrency(id);

		 	   
		 return ResponseEntity.ok(new MessageResponse("Location deleted Successfully !!!"));
	

	}
}
