package com.kim.advertise.Repository;



import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kim.advertise.entity.DepositReciept;
 
@Repository
public interface DepositeRecieptRepository extends JpaRepository<DepositReciept, Long> {

	@Query("select u from DepositReciept u where u.user.id=?1  ")
	List<DepositReciept> findByUser(Long  user_id);
	
	@Query("select u from DepositReciept u where u.user_casher.id=?1 and u.createdDate>=?2 and  u.createdDate<=?3 ")
	List<DepositReciept> findByCasherByDate(Long  user_id,LocalDateTime start,LocalDateTime end);
	
	@Query("select u from DepositReciept u where u.user_casher.id=?1  order by u.user_casher.id  DESC ")
	List<DepositReciept> findByCasher(Long  casher_id);
	

}
