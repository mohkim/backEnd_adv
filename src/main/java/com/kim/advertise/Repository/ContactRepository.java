package com.kim.advertise.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kim.advertise.entity.Contact;
 





@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

 
 
}
