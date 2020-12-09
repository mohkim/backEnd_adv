package com.kim.advertise.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kim.advertise.entity.Address;





@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
 
	 @Query("select u from Address u where u.user.id = ?1")
	 public Address getAddressByUser(Long id);

	 
	 
}
