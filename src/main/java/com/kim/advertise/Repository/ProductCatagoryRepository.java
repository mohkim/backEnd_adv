package com.kim.advertise.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kim.advertise.entity.ProductCatagory;

 

@Repository
public interface ProductCatagoryRepository extends JpaRepository<ProductCatagory, Long> {
	 
}
