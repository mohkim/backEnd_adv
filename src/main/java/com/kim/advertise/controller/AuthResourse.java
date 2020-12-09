package com.kim.advertise.controller;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
 
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
 
import org.springframework.web.bind.annotation.RestController;

import com.kim.advertise.Repository.ConfirmationTokenRepository;
import com.kim.advertise.Repository.RoleRepository;
import com.kim.advertise.Repository.UserRepository;
import com.kim.advertise.Service.EmailSenderService;
import com.kim.advertise.Service.UserService;
import com.kim.advertise.entity.ConfirmationToken;
import com.kim.advertise.entity.ERole;
import com.kim.advertise.entity.Role;
import com.kim.advertise.entity.User;
import com.kim.advertise.form.PasswordChangeForm;
import com.kim.advertise.jwt.JwtResponse;
import com.kim.advertise.jwt.JwtUtils;
import com.kim.advertise.jwt.LoginRequest;
import com.kim.advertise.jwt.MessageResponse;
import com.kim.advertise.jwt.SignupRequest;
import com.kim.advertise.jwt.UserDetailsImpl;

 
 

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/adv/auth")
public class AuthResourse {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;
	
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;
	@Autowired
    private EmailSenderService emailSenderService;
	
	
   @Autowired
   ConfirmationTokenRepository  confirmationRepository;
   
	@Autowired
	JwtUtils jwtUtils;
	
	public String url_domain="http://localhost:4200";
	
	public Logger  log=LoggerFactory.getLogger(this.getClass());

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Optional<User>   op_user=(userRepository.findByUsernameOrEmail(loginRequest.getEmail())) ;
		
        if(!op_user.isPresent()) {
        	return ResponseEntity
					.badRequest()
					.body(new MessageResponse("User not Found by this Email !!!"));
        }
        User  user=op_user.get();
        if(!user.isActive()) {
        	return ResponseEntity
					.badRequest()
					.body(new MessageResponse("User is Not Active Please Go to your Email to activte your Account !!!!"));
        	
        } else if(user.isDisabledbyAdmin()) {
        	return ResponseEntity
					.badRequest()
					.body(new MessageResponse("User disabled by Admin !!"));
        	
        }
        
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}
	@PostMapping("/forgotpassword")   //  i will come back to u later
	public  ResponseEntity<?>  forgotPassword( @RequestBody LoginRequest request){
		String  temp_email=request.getEmail();
	    
		if(!userRepository.existsByEmail(temp_email)) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("User  Not Found !!!!"));
		}
	    	Optional<User> user=userRepository.findByUsernameOrEmail(temp_email);
       
	    	ConfirmationToken ct= confirmationRepository.findByUser(user.get());
	            if (ct != null) {
	                ct.resetToken();
	            } else {  // no token related to the user 
	                ct = new ConfirmationToken(user.get());
	            }
                 confirmationRepository.save(ct);
	            emailSenderService.passwordRecover(ct, user.get());
	           
	            return ResponseEntity.ok(new MessageResponse("email send successfully!"));
           
	}
	@PostMapping("/newpassword/{token}")  
	public  ResponseEntity<?>  newPassword( @RequestBody PasswordChangeForm request,@PathVariable("token") String  token){
		 log.info("Kemal this  the token is sent  : " +request.toString() ); 
		ConfirmationToken ctoken = confirmationRepository.findByConfirmationToken(token);
	  
        if (ctoken != null) {

            if (ctoken.getExpiredate().isBefore(LocalDateTime.now())) {
               
            	return ResponseEntity
    					.badRequest()
    					.body(new MessageResponse("Expired token !!!!"));
                     }
            User user = (userRepository.findByUsernameOrEmail(ctoken.getUser().getEmail())).get();
            log.info("Kemal this  the user is   : " +user.toString());   
             if(user.passwordEquals(request.getNewPassword())) {
            	 return ResponseEntity
     					.badRequest()
     					.body(new MessageResponse("New Password is the same as Old Password "));
            	 
             }else {
            	 user.changePassword(request.getNewPassword());
            	 userRepository.save(user);
                     
                 return ResponseEntity.ok(new MessageResponse("Account Activated Successfully !!"));
             }
           
        } else {
        	  
        	return ResponseEntity
					.badRequest()
					.body(new MessageResponse("broken or Unknown token !!!!"));
        }
 
	          
	}
	@PostMapping("user/{id}/newpassword")  
	public  ResponseEntity<?>  ChangePassword( @RequestBody PasswordChangeForm request,@PathVariable Long id){
	
		
		log.info("change Password data =>"+request.toString());
		User  user=userService.getUser(id);
		 if(user==null) {
			 return ResponseEntity
						.badRequest()
						.body(new MessageResponse("User Not Found!!!")); 
		 }
	  
  
             if(!user.passwordEquals(request.getOldPassword())) {
            	 return ResponseEntity
     					.badRequest()
     					.body(new MessageResponse("Wrong  Old Password "));
            	 
             }else {
            	 
            	 user.changePassword(request.getNewPassword());
            	 userRepository.save(user);
                     
                 return ResponseEntity.ok(new MessageResponse("Password Changed Successfully !!"));
             }
           
         
 
	          
	}
	
	@GetMapping("/activate/{token}")
	public  ResponseEntity<?>  forgotPasswordRecovery( @PathVariable("token") String confirmationToken ){
		ConfirmationToken token = confirmationRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {

            if (token.getExpiredate().isBefore(LocalDateTime.now())) {
               
            	return ResponseEntity
    					.badRequest()
    					.body(new MessageResponse("Expired token !!!!"));
                     }
            User user = (userRepository.findByUsernameOrEmail(token.getUser().getEmail())).get();
            user.setActive(true);
            userRepository.save(user);

            return ResponseEntity.ok(new MessageResponse("Account Activated Successfully !!"));
        } else {
        	  
        	return ResponseEntity
					.badRequest()
					.body(new MessageResponse("broken or Unknown token !!!!"));
        }
                 
	}
 
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		
		
//		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//			return ResponseEntity
//					.badRequest()
//					.body(new MessageResponse("Error: Username is already taken!"));
//		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));
		user.setFullName(signUpRequest.getFullName());
		 

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "ADMIN":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "MANAGER":
					Role manaRole = roleRepository.findByName(ERole.ROLE_MANAGER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(manaRole);

					break;
				case "FINANCE":
					Role finRole = roleRepository.findByName(ERole.ROLE_FINANCE)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(finRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
	 
		
		userRepository.save(user);
		emailSenderService.sendNewActivationCode(
				(userRepository.findByUsernameOrEmail(user.getEmail())).get()
				);
       
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}

