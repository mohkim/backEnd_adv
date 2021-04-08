package com.kim.advertise.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kim.advertise.entity.ProductSubCatagory;
import com.kim.advertise.entity.post.EPostStatus;
import com.kim.advertise.form.CatagoryByQuantity;
import com.kim.advertise.form.SubCatagoryByQuantity;

@Repository
public interface ProductSubCatagoryRepository extends JpaRepository<ProductSubCatagory, Long> {

	@Query("select new com.kim.advertise.form.SubCatagoryByQuantity(t.productSubCatagory.id,"
			+ "t.productSubCatagory.name,   " + "COUNT(*))" + " from Post t where t.post_status.status=?1 and "
			+ " t.productSubCatagory.productCatagory.id=?2 "
			+ "  GROUP BY t.productSubCatagory.name,t.productSubCatagory.id  ")
	public List<SubCatagoryByQuantity> getAllSubCatagoryByQuantity(EPostStatus status, Long id);

}
