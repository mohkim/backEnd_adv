package com.kim.advertise.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kim.advertise.Repository.post.PostRepository;
import com.kim.advertise.Service.HomeService;
import com.kim.advertise.Service.ProductCatagoryService;
import com.kim.advertise.Service.RoleService;
import com.kim.advertise.entity.PictureUpload;
import com.kim.advertise.entity.Role;
import com.kim.advertise.entity.post.Post;
import com.kim.advertise.form.CatagoryByQuantity;
import com.kim.advertise.jwt.MessageResponse;

 
@RestController
@RequestMapping("/adv/")

public class HomeCommonResource {

	@Autowired
	private HomeService homeService;
	
	@Autowired
	PostRepository postRepo;
	@Autowired
	private ProductCatagoryService catService;
 

	@GetMapping("/img/{name}")
	@ResponseBody
	public ResponseEntity<Resource> getImageByName(@PathVariable String name) {
		Resource file = homeService.loadImageByName(name);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	

}
