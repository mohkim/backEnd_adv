package com.kim.advertise.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kim.advertise.Service.PictureUploadService;
import com.kim.advertise.Service.PostService;
import com.kim.advertise.Service.UserService;
import com.kim.advertise.entity.PictureUpload;
import com.kim.advertise.entity.Post;
import com.kim.advertise.entity.User;
import com.kim.advertise.jwt.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/adv")
//@PreAuthorize("hasRole('ROLE_ADMIN')")

public class PostResource {

	@Autowired
	private PostService postService;
	@Autowired
	private PictureUploadService  picService;
	@Autowired
	private UserService  userService;
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/post")
	public List<Post> getAllPost() {
		List<Post> list = postService.allPost();
		return list;
	}

	@GetMapping("/post/{id}")
	public Post getSinglePost(@PathVariable Long id) {
		Post c = postService.getPost(id);
		return c;
	}
	@GetMapping("/user/{id}/post")
	public Post[] getPostByUser(@PathVariable Long id) {
		User user=userService.getUser(id);
		 if(user==null)  return null;
		 
		Post[] postlist = postService.getPostByUser(user);
		return postlist;
	}

	@DeleteMapping("/post/{id}")
	public ResponseEntity<?> deletePost(@PathVariable Long id) {

		// postService.deletePostById(id);
		return ResponseEntity.ok(new MessageResponse("Post deleted Successfully !!!"));

	}

	@PostMapping("user/{id}/post")
	public Long newPost(@Valid @RequestBody Post c,@PathVariable Long  id) {
		log.info("kemal form data ->"+c.toString());
		User  user=userService.getUser(id);
		 c.setUser(user);
		  
		 Post post=postService.save(c);
	   if(post != null)  return post.getId() ;
	   else return  null;

	}
	@PostMapping("user/{id}/post/{postid}")
	public Long editPost(@Valid @RequestBody Post c,@PathVariable Long  id,@PathVariable  Long postid) {
		log.info("kemal form data ->"+c.toString());
		User  user=userService.getUser(id);
		 c.setUser(user);
		 c.setId(postid);
		 Post post=postService.save(c);
	   if(post != null)  return post.getId() ;
	   else return  null;

	}
 

	@PostMapping("/post/{id}/images")
	public ResponseEntity<?> savePostImages(@RequestParam("files") MultipartFile[] files,@PathVariable Long id) {
		Post post = postService.getPost(id);

		if (post == null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Post Not Found!!!"));
		}
		 

  
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

	@DeleteMapping("/post/{id}/image/{name}")
	public ResponseEntity<?> removePostImagesByName(@PathVariable Long id, @PathVariable String name) {
	 
		Post post = postService.getPost(id);
		
		if (post == null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Post Not Found!!!"));
		}

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

	@GetMapping("/post/{id}/image/{name}")
	@ResponseBody
	public ResponseEntity<Resource> getImage(@PathVariable Long id, @PathVariable String name) {
		Post post = postService.getPost(id);

		if (post == null) {
			return null;
		}
		PictureUpload  pic=picService.getByFileName(name);
		if(pic==null)  return null;
   // get the image and return
		
		 Resource file=postService.getUploadFile(pic);
		    return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
 }
