package com.kim.advertise.controller.managment;

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
import com.kim.advertise.entity.post.Post_Status;
import com.kim.advertise.jwt.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/adv")
//@PreAuthorize("hasRole('ROLE_MANAGER')")

public class ManagmentPostResource {

	@Autowired
	private PostService postService;
	@Autowired
	private UserService  userService;
	@GetMapping("/mngt/post_status/{status}")
	public Post[] getAllPostByStatus(@PathVariable String status) {
		return postService.getPostByStatus(status);
	}
 
	@GetMapping("/mngt/post_status/{status}/{id}")
	public Post getAllPostByStatusAndId(@PathVariable String status,@PathVariable Long id) {
		return postService.getPostByStatusAndId(status,id);
	}
	
	@GetMapping("/mngt/post/{pid}/user/{uid}")
	public Post getPostByUser(@PathVariable Long pid,@PathVariable Long uid) {
		Post  p=postService.getPost(pid);
		if(p!=null) {
			Post_Status  p_s=p.getPost_status();
			
			if(p_s==null)  return  null;
			
			
			 if(p_s.getStatus()==EPostStatus.PENDING) {
				 return p;
  			 }else if (p_s.getApprovedByUser()!= null) {
				     if(p_s.getApprovedByUser().getId()==uid)  return p;
				     else return  null;
			 }else if (p_s.getRejectedByUser() != null) {
			     if(p_s.getRejectedByUser().getId()==uid)  return p;
			     else return  null;
			     
			 }	else  return null;
		}
		else  return null;
 }
	
	@PostMapping("/mngt/activate_post/{pid}/user/{uid}")
	public   ResponseEntity<?>  aceptPost(@PathVariable Long pid,@PathVariable Long uid) {
	 
	User  user=userService.getUser(uid);
	Post  post=postService.getPost(pid);
	
	
	
	post.getPost_status().setStatus(EPostStatus.ACTIVE);
	post.getPost_status().setApprovedByUser(user);
	post.getPost_status().setApproved_date(LocalDateTime.now());
    
	if (postService.save(post)!= null) {
		return ResponseEntity.ok(new MessageResponse("Post Aproved  Successfully !!!"));
	} else {
		
	 return ResponseEntity.badRequest().body(new MessageResponse(" Post Aprove failed !!!"));
        
	   }
         }
	
	@PostMapping("/mngt/reject_post/{pid}/user/{uid}")
	public   ResponseEntity<?>  rejectPost(@PathVariable Long pid,@PathVariable Long uid, @RequestBody String reject_reason) {
	
   
		User  user=userService.getUser(uid);
		Post  post=postService.getPost(pid);
		
		post.getPost_status().setStatus(EPostStatus.ERROR);
		post.getPost_status().setRejectedByUser(user);
		post.getPost_status().setRejectionReason(reject_reason);;
		post.getPost_status().setRejected_date(LocalDateTime.now());
	    
		if (postService.save(post)!= null) {
			return ResponseEntity.ok(new MessageResponse("Post Rejection Process  Successfully !!!"));
			
		} else {
			
		 return ResponseEntity.badRequest().body(new MessageResponse("Post Rejection Process  failed !!!"));
	        
		   }
	 }
	
	@GetMapping("/mngt/post_rejected/user/{uid}")
	public   Post[]  getAllPostRejectedByUser(@PathVariable Long uid) {
		
		return postService.getPostRejectedByUser(uid);
	}
	
	@GetMapping("/mngt/post_accepted/user/{uid}")
	public   Post[]  getAllPostAcceptedByUser(@PathVariable Long uid) {
		return postService.getPostAcceptedByUser(uid);
	}
	
 }
