package com.kim.advertise.Repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kim.advertise.entity.DepositReciept;
 





@Repository
public interface UserDepositeRecieptRepository extends JpaRepository<DepositReciept, Long> {

	@Query("select u from DepositReciept u where u.user.id=?1  ")
	List<DepositReciept> findByUser(Long  user_id);
	
	@Query("select u from DepositReciept u where u.user_casher.id=?1  ")
	List<DepositReciept> findByCasher(Long  user_id);
	
	@Query("select u from DepositReciept u where u.receiptNo=?1  ")
	Optional<DepositReciept> findByReceiptNo(String receipt_No);
}
