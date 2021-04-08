package com.kim.advertise.controller.user;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kim.advertise.Service.PictureUploadService;
import com.kim.advertise.Service.UserService;
import com.kim.advertise.Service.post.PostService;
import com.kim.advertise.entity.PictureUpload;
import com.kim.advertise.entity.User;
import com.kim.advertise.entity.post.Post;
import com.kim.advertise.jwt.MessageResponse;

 
@RestController
@RequestMapping("/adv/user/")
@PreAuthorize("hasRole('ROLE_USER')")

public class UserPostResource {

	@Autowired
	private PostService postService;
	@Autowired
	private PictureUploadService  picService;
	@Autowired
	private UserService  userService;
	private Logger log = LoggerFactory.getLogger(this.getClass());
 
	 
	@GetMapping("post/user/{uid}")
	public  Post[] getAllPostByUser(@PathVariable  Long  uid ){
	
		return  postService.getPostByUserById(uid);
		 
	 }
	
	@GetMapping("post_status/{status}/user/{uid}")
	public Post[] getAllPostByStatus(@PathVariable String status ,@PathVariable Long uid ) {
		
		return postService.getAllPostByStatusOfUser(status,uid);
		
	}
	
	@GetMapping("/post/{pid}/user/{uid}")
	public Post getPostByIdOfUser(@PathVariable Long pid, @PathVariable Long uid) { 
		
	  Post p= postService.getPost(pid);
		
	  if(p!=null ) {  // if  post not found 
		  if(p.getUser().getId()==uid) {
			  return  p; 
		  }else {
			  return null;
		  }
	  }
	  else return null;
	  
	  
		
	}
	
	// below for deleting and saving post 
	
	@DeleteMapping("/post/{pid}/user/{uid}")
	 public ResponseEntity<?> deletePost(@PathVariable Long pid ,@PathVariable Long uid) {

		
		Post  p=postService.getPost(pid);
		
		if(p!=null) {
			if(p.getUser().getId()==uid) {
				// postService.deletePostById(id);
				return ResponseEntity.ok(new MessageResponse("Post deleted Successfully !!!"));
			}
			else 	return ResponseEntity.badRequest().body(new MessageResponse("Owner Mispatch")); 
			
		}else  	return ResponseEntity.badRequest().body(new MessageResponse("Post Not Found!!!"));
		

	}

	@PostMapping("user/{id}/post")
	 public Long savePost(@Valid @RequestBody Post c,@PathVariable Long  id) {
		log.info("kemal form data ->"+c.toString());
		User  user=userService.getUser(id);
		 c.setUser(user);
		  
		 Post post=postService.save(c);
	   if(post != null)  return post.getId() ;
	   else return  null;

	}

	@PostMapping("user/{uid}/post/{pid}")
	 public Long editPost(@Valid @RequestBody Post c,@PathVariable Long  uid,@PathVariable  Long pid) {
		log.info("kemal form data ->"+c.toString());
		User  user=userService.getUser(uid);
		 c.setUser(user);
		 c.setId(pid);
		 Post post=postService.save(c);
	   if(post != null)  return post.getId() ;
	   else return  null;

	}
 

	@PostMapping("/post/{id}/images/user/{uid}")
	 public ResponseEntity<?> savePostImages(@RequestParam("files") MultipartFile[] files,@PathVariable Long id,@PathVariable Long uid) {
		Post post = postService.getPost(id);
        
		
		
		if (post == null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Post Not Found!!!"));
			
		} else if(post.getUser().getId()!=uid) return ResponseEntity.badRequest().body(new MessageResponse(" owner Mismatch !!!"));
	
		try {
			for (int i = 0; i < files.length; i++) {
				
				if(!postService.addUploadFile(files[i], post,i)) {
					return ResponseEntity.badRequest().body(new MessageResponse("Post Image   file upload failed !!!"));
					 
					
				};
			}

			return ResponseEntity.ok(new MessageResponse("Post Image   Saved Successfully !!!"));

		} catch (Exception e) {

			return ResponseEntity.badRequest().body(new MessageResponse("Post Image   file upload failed !!!"));

		}
	}
 
	
	@DeleteMapping("/post/{id}/image/{name}/user/{uid}")
	public ResponseEntity<?> removePostImagesByName(@PathVariable Long id, @PathVariable String name ,@PathVariable Long uid) {
	 
		Post post = postService.getPost(id);
		
		if (post == null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Post Not Found!!!"));
			
		} else if(post.getUser().getId()!=uid) return ResponseEntity.badRequest().body(new MessageResponse(" owner Mismatch !!!"));

		PictureUpload  pic=picService.getByFileName(name);
		if(pic ==null ) {
			return ResponseEntity.badRequest().body(new MessageResponse("Picture Not Found  By Name!"));
		}
		try {
			postService.removeUploadFile(pic, post);

			return ResponseEntity.ok(new MessageResponse("Post Image Deleted Successfully !!!"));

		} catch (Exception e) {

			return ResponseEntity.badRequest().body(new MessageResponse("Image Deletion Fail failed !!!"));

		}

	}
	
 	@GetMapping("/post/{id}/images")
	public  List<PictureUpload>  getPostImages(@PathVariable  Long  id){
		Post post = postService.getPost(id);

		if (post == null) {
			return null;
		}
		return  post.getPostImage();
	}

 	 
 }
