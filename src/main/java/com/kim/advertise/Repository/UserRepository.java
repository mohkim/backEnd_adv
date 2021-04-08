package com.kim.advertise.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kim.advertise.entity.User;
import com.kim.advertise.entity.view.UserView;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u where u.id=?1 ")
	UserView getUserById(Long id);

	Optional<User> findByUsername(String username);

	@Query("select u from User u where u.username = ?1 or u.email = ?1 ")
	Optional<User> findByUsernameOrEmail(String signIn);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
