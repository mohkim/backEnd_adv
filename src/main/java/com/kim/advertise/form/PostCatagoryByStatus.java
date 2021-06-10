package com.kim.advertise.form;

import com.kim.advertise.entity.emum.EPostStatus;

public class PostCatagoryByStatus {

	 
	private EPostStatus status;
 	private Long qty;
 	
	public PostCatagoryByStatus() {
		super();
	}
	public PostCatagoryByStatus(EPostStatus status, Long qty) {
		super();
 
		this.status = status;
		this.qty = qty;
	}
	 
	public EPostStatus getStatus() {
		return status;
	}
	public void setStatus(EPostStatus status) {
		this.status = status;
	}
	public Long getQty() {
		return qty;
	}
	public void setQty(Long qty) {
		this.qty = qty;
	}
 	
 	 
}