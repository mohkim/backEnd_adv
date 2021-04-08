/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kim.advertise.Repository;

import org.springframework.data.repository.CrudRepository;

import com.kim.advertise.entity.ConfirmationToken;
import com.kim.advertise.entity.User;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
	/**
	 * Find ConfirmationToken by ConfirmationToken String @
	 * ConfirmationTokenRepository == > Confirmation token string
	 */
	ConfirmationToken findByConfirmationToken(String confirmationToken);

	/**
	 * Find Confirmation token by User
	 *{@link User}  ==> user Object 
	 */
	public ConfirmationToken findByUser(User user);

}
