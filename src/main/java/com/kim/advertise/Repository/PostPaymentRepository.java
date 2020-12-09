package com.kim.advertise.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kim.advertise.entity.PostPayment;
 
@Repository
public interface PostPaymentRepository extends JpaRepository<PostPayment, Long> {
	 
}
