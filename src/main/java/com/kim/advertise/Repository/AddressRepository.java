package com.kim.advertise.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kim.advertise.entity.Address;





/**
 * @author USER_K
 *
 */
 

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
 
	/**
	 * extract addres by User Id
	 *  @param id  =>  id of the user
	 *
	 */
	 @Query("select u from Address u where u.user.id = ?1")
	 public Address getAddressByUser(Long id);

	 
	 
}
