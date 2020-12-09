package com.kim.advertise.jwt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kim.advertise.Repository.UserRepository;
import com.kim.advertise.entity.User;

 

 

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	User user = userRepository.findByUsernameOrEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
	return UserDetailsImpl.build(user);
	} 
	 }
