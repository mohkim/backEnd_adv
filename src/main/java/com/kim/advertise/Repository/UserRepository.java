package com.kim.advertise.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kim.advertise.entity.User;
import com.kim.advertise.entity.post.EPostStatus;
import com.kim.advertise.entity.view.UserView;
import com.kim.advertise.form.CatagoryByQuantity;
import com.kim.advertise.form.PostCatagoryByStatus;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	 @Query("select new com.kim.advertise.form.PostCatagoryByStatus("
		 		+ "t.post_status.status,COUNT(*))"
		 		+ " from Post t  where  t.user.id=?1  GROUP BY t.post_status.status ")  
			 public List<PostCatagoryByStatus> getAllPostByStatusAndUser(Long user_id);
		 
	
	
	
	
	@Query("select u from User u where u.id=?1 ")
	UserView getUserById(Long id);

	Optional<User> findByUsername(String username);

	@Query("select u from User u where u.username = ?1 or u.email = ?1 ")
	Optional<User> findByUsernameOrEmail(String signIn);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
