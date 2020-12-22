/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kim.advertise.Service;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.kim.advertise.Repository.UserRepository;
import com.kim.advertise.controller.UserResource;
import com.kim.advertise.entity.PictureUpload;
import com.kim.advertise.entity.User;
import com.kim.advertise.utility.Utility;

/**
 *
 * @author FREE_MIND
 */

@Repository
public class UserService {

	
	@Autowired
	public UserRepository userRepo;
	@Autowired
	public PictureUploadService picService;
	private final Path root = Paths.get("E:\\upload");
	 
    
	Logger  log=LoggerFactory.getLogger(UserService.class);
	
	public boolean save(User user) {

		User c = userRepo.save(user);
		if (c == null)
			return false;
		else
			return true;

	}

	public boolean updateUser(User user, Long id) {
		User ct = (userRepo.findById(id)).get();
		if (ct != null) {
			ct = user;
			ct.setId(id);

			if (userRepo.save(ct) != null)
				return true;
			else
				return false;
		} else
			return false;
	}

	public void deleteUserById(Long c_Id) {
		userRepo.deleteById(c_Id);
	}

	public List<User> allUser() {
		return userRepo.findAll();
	}

	public User getUser(Long id) {
		Optional<User> c = userRepo.findById(id);
		if (c.isPresent()) {
			return c.get();
		} else
			return null;
	}

	public boolean saveUserImage(MultipartFile file, User user) {
		log.info(" save User Image function ");
		try {
		    if(user.getProfile_image() !=null) {
		        Files.deleteIfExists(this.root.resolve(user.getProfile_image().getName()));
			 } 
			PictureUpload pic = new PictureUpload();
		    pic.setName(setUserProfileFileName(file.getOriginalFilename(),user.getId()));
			Files.copy(file.getInputStream(), this.root.resolve(pic.getName()));
			 
			  
			String url = MvcUriComponentsBuilder
			          .fromMethodName(UserResource.class, "getImage", user.getId(),pic.getName()).build().toString();
			
			log.info("Image upload name => "+url);
			pic.setUrl(url);
			pic = picService.save(pic);
			user.setProfile_image(pic);
			save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
//             throw new RuntimeException("Could not store the file. Error: " + e.getMessage());

		}
	}

	public Resource loadUserImage(Long id) {
		PictureUpload pic=getUser(id).getProfile_image();
		try {
			Path file = root.resolve(pic.getName());
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	public String setUserProfileFileName(String file, Long user_id) {
		String name = Utility.getStringDateTimeofNow() + "_" + user_id + "_." + Utility.getExtenstion(file);
		return name;
	}
	
	
	
}
