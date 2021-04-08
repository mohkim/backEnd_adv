package com.kim.advertise.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kim.advertise.entity.ProductCatagory;
import com.kim.advertise.entity.post.EPostStatus;
import com.kim.advertise.form.CatagoryByQuantity;
 

 

@Repository
public interface ProductCatagoryRepository extends JpaRepository<ProductCatagory, Long> {

 @Query("select new com.kim.advertise.form.CatagoryByQuantity(t.productSubCatagory.productCatagory.id,"
 		+ "t.productSubCatagory.productCatagory.name,t.productSubCatagory.productCatagory.img,   "
 		+ "COUNT(*))"
 		+ " from Post t  where t.post_status.status=?1  GROUP BY t.productSubCatagory.productCatagory.name,t.productSubCatagory.productCatagory.id ,t.productSubCatagory.productCatagory.img ")  
	 public List<CatagoryByQuantity> getAllCatagoryByQuantity(EPostStatus status);
}
