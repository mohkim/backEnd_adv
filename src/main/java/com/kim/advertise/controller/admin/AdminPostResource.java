package com.kim.advertise.controller.admin;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kim.advertise.Service.UserService;
import com.kim.advertise.Service.post.PostService;
import com.kim.advertise.entity.User;
import com.kim.advertise.entity.post.EPostStatus;
import com.kim.advertise.entity.post.Post;
import com.kim.advertise.jwt.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/adv")
//@PreAuthorize("hasRole('ROLE_MANAGER')")

public class AdminPostResource {

	@Autowired
	private PostService postService;
	@Autowired
	private UserService  userService;
	@GetMapping("/admin/post_status/{status}")
	public Post[] getAllPostByStatus(@PathVariable String status) {
		return postService.getPostByStatus(status);
	}
 
	@GetMapping("/admin/post/{id}")
	public Post getAllPostById(@PathVariable Long id) {
		return postService.getPost(id);
	}
	
	@PostMapping("/admin/disable_post/{pid}/user/{uid}")
	public ResponseEntity<?> enableDisablePost(@PathVariable Long pid, @PathVariable Long uid,
			@RequestBody Boolean disable) {

		User user = userService.getUser(uid);
		Post post = postService.getPost(pid);
		
		if (disable) {  // disalbling (true )  post
			post.getPost_status().setStatus(EPostStatus.DISABLED);
			post.getPost_status().setDiasabledByUser(user);
			post.getPost_status().setDisabled_date(LocalDateTime.now());

			if (postService.save(post) != null) {
				return ResponseEntity.ok(new MessageResponse("Post Disalbled Successfully !!!"));
			} else {

				return ResponseEntity.badRequest().body(new MessageResponse(" Post Disable failed !!!"));

			}
		} else  { 
			    
			post.getPost_status().setStatus(EPostStatus.ACTIVE);
			post.getPost_status().setDiasabledByUser(null);
			post.getPost_status().setDisabled_date(null);
	        
			  
			if (postService.save(post) != null) {
				return ResponseEntity.ok(new MessageResponse("Post Enable Successfully !!!"));
			} else {

				return ResponseEntity.badRequest().body(new MessageResponse(" Post Enable failed !!!"));

			} 
			  } 	
		         }
	
 
	
 }
