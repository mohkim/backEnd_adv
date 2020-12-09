package com.kim.advertise.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kim.advertise.entity.Post;
import com.kim.advertise.entity.User;

 

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

 public 	Post[] findByUser(User user);
	 
}
