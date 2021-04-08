package com.kim.advertise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kim.advertise.Service.PictureUploadService;
import com.kim.advertise.Service.UserService;
import com.kim.advertise.Service.post.PostService;
import com.kim.advertise.entity.PictureUpload;
import com.kim.advertise.entity.User;
import com.kim.advertise.entity.post.EPostStatus;
import com.kim.advertise.entity.post.Post;
import com.kim.advertise.entity.view.UserView;

 
@RestController
@RequestMapping("/adv")
public class HomePostResource {

	@Autowired
	private PostService postService;
	@Autowired
	private PictureUploadService  picService;
	@Autowired
	private UserService  userService;
	
	@GetMapping("/user/{id}")   // for test not complete
	public UserView getAllPost(@PathVariable Long id) {
		return  userService.getUserById(id);
      }	
	@GetMapping("/post")
	public Post[] getAllPost() {
		return  postService.getAllActivePost();
      }

	
	@GetMapping("/post/cat/{id}")
	public Post[] getPostByCat(@PathVariable Long id) {
    return postService.getAllPostByCatagory_active(id);
  
      }
	
	@GetMapping("/post/subcat/{id}")
	public Post[] getPostBySubCat(@PathVariable Long id) {
		 return postService.getAllPostBySubCatagory_active(id);
      }
	@GetMapping("/post/{id}")
	public Post getSinglePost(@PathVariable Long id) {
		return postService.getActivePostById(id);
		 
	}
	@GetMapping("/user/{id}/post")
	public Post[] getPostByUser(@PathVariable Long id) {
		User user=userService.getUser(id);
		 if(user==null) {
			 return null;
		 } else if(user.isDisabledbyAdmin()) { // check for user disable
			 return null;
		 }
		 
		Post[] postlist = postService.getActivePostByUser(user);
		return postlist;
	}
 	@GetMapping("/post/{id}/images")
	public  List<PictureUpload>  getPostImages(@PathVariable  Long  id){
		Post post = postService.getActivePostById(id);

		if (post == null) {
			return null;
		} 
		return  post.getPostImage();
	}

	@GetMapping("/post/{id}/image/{name}")
	@ResponseBody
	public ResponseEntity<Resource> getImage(@PathVariable Long id, @PathVariable String name) {
		Post post = postService.getPost(id);
 // above code should only  get  images of active post
		//but  current all the images source from this post
		if (post == null) {
			return null;
		}
		PictureUpload  pic=picService.getByFileName(name);
		if(pic==null)  return null;
    
		
		 Resource file=postService.getUploadFile(pic);
		    return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
 }
