package com.kim.advertise.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kim.advertise.entity.SpecificationHeadOption;

 

@Repository
public interface SpecificationHeadOptionRepository extends JpaRepository<SpecificationHeadOption, Long> {
	 
	 
}
