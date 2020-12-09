package com.kim.advertise.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kim.advertise.entity.SalesLocation;





@Repository
public interface SalesLocationRepository extends JpaRepository<SalesLocation, Long> {
 
}
