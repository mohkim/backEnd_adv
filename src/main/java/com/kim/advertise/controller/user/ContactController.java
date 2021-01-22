package com.kim.advertise.controller.user;

 

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

import com.kim.advertise.Service.ContactService;
import com.kim.advertise.Service.UserService;
import com.kim.advertise.entity.Contact;
import com.kim.advertise.entity.User;
import com.kim.advertise.jwt.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/adv")

public class ContactController {

	@Autowired
	private ContactService contactService;

	@Autowired
	private UserService userServ;

	@GetMapping("/user/{id}/contact")
	public Contact getContactByUser(@PathVariable Long id) {
		User user = userServ.getUser(id);

		if (user == null) {
			return null;
		}

		return contactService.getByUser(user);
	}

	@PostMapping("/user/{id}/contact")
	@PreAuthorize("hasRole('ROLE_USER')")	
	public ResponseEntity<?> saveNewContact(@Valid @RequestBody Contact c, @PathVariable Long id) {

		User user = userServ.getUser(id);

		if (user == null) {
			return ResponseEntity.ok(new MessageResponse("User Not Found  !!!"));
		}

		c.setUser(user);

		if (contactService.save(c) != null) {
			return ResponseEntity.ok(new MessageResponse("Contact Saved Successfully !!!"));
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse(" Contact Save failed !!!"));
		}

	}

	@DeleteMapping("/user/contact/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> deleteContact(@PathVariable Long id) {
		contactService.deleteContact(id);
     return ResponseEntity.ok(new MessageResponse("Contact deleted Successfully !!!"));

	}
}
