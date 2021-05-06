package com.kim.advertise.controller.user;

import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kim.advertise.Service.RoleService;
import com.kim.advertise.Service.UserService;
import com.kim.advertise.entity.Role;
import com.kim.advertise.entity.User;
import com.kim.advertise.entity.post.ERole;
import com.kim.advertise.form.PostCatagoryByStatus;
import com.kim.advertise.jwt.MessageResponse;

 
@RestController
@RequestMapping("/adv/admin")
public class UserResource {

 
	@Autowired
	private UserService  userService;
	@Autowired
	private RoleService  roleService;
	 private Logger  log=LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/user")
	public List<User> getAllUser() {
	 List<User>  list=userService.allUser();
		return   list;
	}
	@GetMapping("/user/{id}")
    public User getSingleUser(@PathVariable  Long id) {
	
		User c=userService.getUser(id);
		return   c;
		
	}
	@GetMapping("/user/{id}/role")
    public Set<Role> getAllUserRole(@PathVariable  Long id) {
	 return userService.getUser(id).getRoles();
		 
	}
	@DeleteMapping("/user/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable  Long id) {
	        
		// userService.deleteUserById(id);
		 return ResponseEntity.ok(new MessageResponse("User deleted Successfully !!!"));
			  
		 
	}
	@PostMapping("/user")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> saveUser( @Valid @RequestBody User c) {
		log.info("Sub catagory is kemal ->"+c.toString());
		if(userService.save(c)) { 
			  return ResponseEntity.ok(new MessageResponse("User Saved Successfully !!!"));
		}
		else {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("User save failed !!!"));
		}
		 
	}
	@PostMapping("/user/{id}/updateusername/{name}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateUserName( @PathVariable Long id , @PathVariable  String name) {
	 
		User  user=userService.getUser(id);
		
		 if(user==null) {
			 return ResponseEntity
						.badRequest()
						.body(new MessageResponse("User Not Found!!!")); 
		 }
		 user.setUsername(name);
		  
		if(userService.save(user)) { 
			  return ResponseEntity.ok(new MessageResponse("User Name Updated  Successfully !!!"));
		}
		else {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("User Name Update failed !!!"));
		}
		 
	}
 
	@PostMapping( "/user/{id}/image")
	@PreAuthorize("hasRole('ROLE_USER')")
	public  ResponseEntity<?>  saveUserImage( @RequestParam("file") MultipartFile file,@PathVariable Long id ) {
		User  user=userService.getUser(id);
		
		
		 if(user==null)
			return ResponseEntity.badRequest().body(new MessageResponse("User Not Found!!!"));
		 
		
	    try {
	   userService.saveUserImage(file,user);

	      return ResponseEntity.ok(new MessageResponse("User Image file Saved Successfully !!!"));
	    } catch (Exception e) {
	    	return ResponseEntity
					.badRequest()
					.body(new MessageResponse("User Image file upload failed !!!"));
	    }
	  }
	
	@GetMapping( "/user/{id}/image")
public  ResponseEntity<?> getUserImageUlr( @PathVariable Long  id ) {
		User  user=userService.getUser(id);
		 if(user==null) 	return ResponseEntity.badRequest().body(new MessageResponse(""));
		  return ResponseEntity.ok(new MessageResponse(user.getImage_name()));  
	  }
	
	@GetMapping("/user/{id}/image/{name}")
	@ResponseBody
	public ResponseEntity<Resource> getImage(@PathVariable Long id, @PathVariable String name) {
		 Resource file=userService.loadUserImage(id);
		    return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	} 
	
	@DeleteMapping("/user/{id}/role/{rid}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteUserRole(@PathVariable  Long id,@PathVariable  Long rid) {
	        
		User  u=userService.getUser(id);
		Role  r=roleService.getRole(rid);
		if(r.getName().equals(ERole.ROLE_USER)) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("User Role Cannot be Deleted !!!"));
		 }else {
			 u.removeRole(r);
			 userService.save(u);
			 return ResponseEntity.ok(new MessageResponse("Role deleted Successfully !!!"));
		 }
	  
	}
	@GetMapping("/user/{id}/role/{rid}")
    public ResponseEntity<?> addUserRole(@PathVariable  Long id,@PathVariable  Long rid) {
		 
		User  u=userService.getUser(id);
		Role  r=roleService.getRole(rid);
		Set<Role> list=u.getRoles();
		  if(list.contains(r)) {
		    	return ResponseEntity
						.badRequest()
						.body(new MessageResponse("User  already has the Role !!!"));
		  }else {
			  u.addRole(r);
			  userService.save(u);
			  return ResponseEntity.ok(new MessageResponse("User Role Saved Successfully !!!"));
		  }
	 	 
	}
 
	@GetMapping("/user/{id}/post_catagory_status")
    public List<PostCatagoryByStatus> getPostCatagoryByStatusOfUser(@PathVariable  Long id) {
	   return   userService.getPostCatagoryByStatus(id);
	 }
}

