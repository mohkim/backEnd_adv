package com.kim.advertise.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kim.advertise.entity.Currency;





@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
 
}
