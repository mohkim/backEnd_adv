/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kim.advertise.Repository;

 
import org.springframework.data.repository.CrudRepository;

import com.kim.advertise.entity.ConfirmationToken;
import com.kim.advertise.entity.User;

/**
 *
 * @author FREE_MIND
 */
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
   public ConfirmationToken findByUser(User  user);

 
}
