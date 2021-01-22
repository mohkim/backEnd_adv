package com.kim.advertise.controller;

import java.util.List;

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

import com.kim.advertise.Service.RoleService;
import com.kim.advertise.entity.Role;
import com.kim.advertise.jwt.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/adv/admin")


public class RoleResource {

 
	@Autowired
	private RoleService  roleService;
	
	 private Logger  log=LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/role")
	public List<Role> getAllRole() {
	 List<Role>  list=roleService.allRole();
		return   list;
	}
	@GetMapping("/role/{id}")
    public Role getSingleRole(@PathVariable  Long id) {
	 Role c=roleService.getRole(id);
		return   c;
	}
	@DeleteMapping("/role/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteRole(@PathVariable  Long id) {
	        
		// roleService.deleteRoleById(id);
		 return ResponseEntity.ok(new MessageResponse("Role deleted Successfully !!!"));
			  
		 
	}
	@PostMapping("/role")
@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> saveRole( @Valid @RequestBody Role c) {
		 
		if(roleService.save(c)) { 
			  return ResponseEntity.ok(new MessageResponse("Role Saved Successfully !!!"));
		}
		else {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Role save failed !!!"));
		}
		 
	}
	 
}

