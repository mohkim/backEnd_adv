package com.kim.advertise.Repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kim.advertise.entity.User;
import com.kim.advertise.entity.post.Post;
import com.kim.advertise.entity.post.Post_Status;

 

@Repository
public interface PostStatusRepository extends JpaRepository<Post_Status, Long> {

 
	 
}
