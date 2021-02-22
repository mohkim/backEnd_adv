/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kim.advertise.Service.post;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.kim.advertise.Repository.post.PostRepository;
import com.kim.advertise.Service.PictureUploadService;
import com.kim.advertise.Service.UserService;
import com.kim.advertise.controller.PostResource;
import com.kim.advertise.entity.PictureUpload;
import com.kim.advertise.entity.User;
import com.kim.advertise.entity.post.EPostStatus;
import com.kim.advertise.entity.post.Post;
import com.kim.advertise.utility.Utility;


@Repository
public class PostService {

	@Autowired
	public PostRepository postRepo;
	@Autowired
	public UserService userService;
	
	@Autowired
	public PictureUploadService picService;
	private final Path root = Paths.get("K:\\upload\\post");

	public Post save(Post post) {

		Post c = postRepo.save(post);
		if (c == null)
			return null;
		else
			return c;

	}

	public void deletePostById(Long c_Id) {
		postRepo.deleteById(c_Id);
	}

	public List<Post> allPost() {
		return postRepo.findAll();
	}

	public Post getPost(Long id) {
		Optional<Post> c = postRepo.findById(id);
		if (c.isPresent()) {
			return c.get();
		} else
			return null;
	}

	public Resource getAllPosteImages(Long id) {

		return null;
	}

	public boolean addUploadFile(MultipartFile file, Post post,Integer  index) {
		try {

			PictureUpload pic = new PictureUpload();
			pic.setName(this.getPostProfileFileName(file.getOriginalFilename(), post,index));
			Files.copy(file.getInputStream(), this.root.resolve(pic.getName()));
             
			String url = MvcUriComponentsBuilder
			          .fromMethodName(PostResource.class, "getImage", post.getId(),pic.getName()).build().toString();
			
			pic.setUrl(url);
		 
		//	pic = picService.save(pic);
			post.addPostImage(pic);
			save(post);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
//   				      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());

		}
	}

	public void removeUploadFile(PictureUpload pic, Post post) {

		 try {
			Files.deleteIfExists(this.root.resolve(pic.getName()));
		} catch (IOException e) {
			 
			e.printStackTrace();
		}
		post.removePostImage(pic);
		postRepo.save(post);

	}
	public Resource getUploadFile(PictureUpload pic) {
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

	public String getPostProfileFileName(String file, Post post,Integer index) {
		String name = Utility.getStringDateTimeofNow() + "_" + post.getUser().getId() + "_" + post.getId() + "_"+ index+"_."
				+ Utility.getExtenstion(file);
		return name;
	}

	public Post[] getPostByUser(User user) {
		  return  postRepo.findByUser(user);
	 
	}
	
	
	/** return a list of Posts for user , but only posts that belongs to him only 
	 * @param id   user  id 
	 * @return     list of post 
	 */
	public Post[] getPostByUserById(Long id) {
		 return  postRepo.findByUser(userService.getUser(id));
	 
	}
	
	public  Post[]  getPostByStatus(String status) {
	EPostStatus estatus;
		try {
			estatus=EPostStatus.valueOf(status)	;
			
			if(estatus.equals(EPostStatus.ACTIVE)) {
				return  postRepo.findPostByStatusAproved(estatus);
			}else  if(estatus.equals(EPostStatus.PENDING)) {
				return  postRepo.findPostByStatusPending(estatus);
			}else  if(estatus.equals(EPostStatus.ERROR)) {
				return  postRepo.findPostByStatusError(estatus);
			}else  if(estatus.equals(EPostStatus.DISABLED)) {
				return  postRepo.findPostByStatusDisabled(estatus);
			} else  return null;
			
			
			
		} catch (Exception e) {
		return null;
		}
		
	}

	public Post getPostByStatusAndId(String status, Long id) {
		EPostStatus estatus;
		try {
			estatus=EPostStatus.valueOf(status)	;
			return  postRepo.findPostByStatusAndId(estatus,id);
		} catch (Exception e) {
		return null;
		}
	}
	
	public Post[]  getPostRejectedByUser(Long id) {
	
		return  postRepo.findPostRejectedByUser(EPostStatus.ERROR,id);
		
	}
	
	
 
    public Post[]  getPostAcceptedByUser(Long id) {
		
		return  postRepo.findPostAcceptedByUser(EPostStatus.ACTIVE,id);
	
	}

 
   
	/** Get all Post of Specific User  with status
	 * @param status    Post satus
	 * @param uid  user  Id
	 * @return  list of  Post
	 */
	public Post[] getAllPostByStatusOfUser(String status, Long uid) {
		EPostStatus estatus;
		try {
			estatus=EPostStatus.valueOf(status)	;
		   return  postRepo.findPostByStatusOfUser(estatus, uid);
			
			
		} catch (Exception e) {
		return null;
		}
	}

	public Post[] getAllActivePost() {
       return  postRepo.findAllActivePost(EPostStatus.ACTIVE);
		 
	}

	public Post getActivePostById(Long id) {
		return  postRepo.findActivePostById(EPostStatus.ACTIVE ,id);
	}

	public Post[] getActivePostByUser(User user) {
		return  postRepo.findActivePostByUser(EPostStatus.ACTIVE ,user.getId());
	}
}
