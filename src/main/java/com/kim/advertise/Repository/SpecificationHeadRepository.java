package com.kim.advertise.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kim.advertise.entity.SpecificationHead;

 

@Repository
public interface SpecificationHeadRepository extends JpaRepository<SpecificationHead, Long> {
	 
	 
}
