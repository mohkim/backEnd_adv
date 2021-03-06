package com.kim.advertise.entity.post;

import javax.persistence.*;

import com.kim.advertise.entity.emum.EPostServiceFree;

 

@Entity
 
public class PostRecipt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
     
	private   EPostServiceFree  feeOption;
	
	 private   String  description;
	private String receiptNo;
	
	
	private  Integer  amount;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public EPostServiceFree getFeeOption() {
		return feeOption;
	}

	public void setFeeOption(EPostServiceFree feeOption) {
		this.feeOption = feeOption;
	}
	
	
	
	    
}