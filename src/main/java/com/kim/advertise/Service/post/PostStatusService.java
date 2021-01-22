/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kim.advertise.Service.post;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kim.advertise.Repository.post.PostStatusRepository;
import com.kim.advertise.entity.post.Post_Status;

/**
 *
 * @author FREE_MIND
 */

@Repository
public class PostStatusService {

	@Autowired
	public PostStatusRepository postStatusRepo;
	

    
	Logger  log=LoggerFactory.getLogger(PostStatusService.class);
	
	public boolean save(Post_Status role) {

		Post_Status c = postStatusRepo.save(role);
		if (c == null)
			return false;
		else
			return true;

	}
   public void deletePost_StatusById(Long id) {
		postStatusRepo.deleteById(id);
	}

	public List<Post_Status> allPost_Status() {
		return postStatusRepo.findAll();
	}

	public Post_Status getPost_Status(Long id) {
		Optional<Post_Status> c = postStatusRepo.findById(id);
		if (c.isPresent()) {
			return c.get();
		} else
			return null;
	}

 
}
