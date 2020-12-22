/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kim.advertise.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kim.advertise.Repository.RoleRepository;
import com.kim.advertise.entity.ERole;
import com.kim.advertise.entity.Role;

/**
 *
 * @author FREE_MIND
 */

@Repository
public class RoleService {

	
	@Autowired
	public RoleRepository roleRepo;
	

    
	Logger  log=LoggerFactory.getLogger(RoleService.class);
	
	public boolean save(Role role) {

		Role c = roleRepo.save(role);
		if (c == null)
			return false;
		else
			return true;

	}
   public void deleteRoleById(Long c_Id) {
		roleRepo.deleteById(c_Id);
	}

	public List<Role> allRole() {
		return roleRepo.findAll();
	}

	public Role getRole(Long id) {
		Optional<Role> c = roleRepo.findById(id);
		if (c.isPresent()) {
			return c.get();
		} else
			return null;
	}

	 public Role  getRoleByName(ERole name) {
		 return   roleRepo.findByName(name).get();		 
	 }
}
