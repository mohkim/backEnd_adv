package com.kim.advertise.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kim.advertise.entity.ProductSubCatagory;

 

@Repository
public interface ProductSubCatagoryRepository extends JpaRepository<ProductSubCatagory, Long> {
	 
	 
}
