package com.kim.advertise.form;

 
import com.kim.advertise.entity.ProductSubCatagory;

public class SubCatagoryByQuantity {


	private Long id;
	private String name;
 	private Long qty;
 	
 	
	public SubCatagoryByQuantity(Long id, String name, Long qty) {
		super();
		this.id = id;
		this.name = name;
		this.qty = qty;
	}
	
	
	public SubCatagoryByQuantity() {
		super();
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getQty() {
		return qty;
	}
	public void setQty(Long qty) {
		this.qty = qty;
	}
 	
 	
}
