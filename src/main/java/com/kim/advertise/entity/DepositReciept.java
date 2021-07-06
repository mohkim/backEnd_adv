package com.kim.advertise.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import  static   com.kim.advertise.utility.Utility.getReceiptNumber;

@Entity

public class DepositReciept {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	@OneToOne 
	@JoinColumn(name = "fk_user")
	private User user;

	private String description;
	 

	private Integer amount;

	private boolean receipt_void = false;
 
    @CreationTimestamp
    public LocalDateTime createdDate;
    @UpdateTimestamp
    public LocalDateTime  updatedDate;

	@ManyToOne
	@JoinColumn(name = "fk_casher")
	private User user_casher;
	@ManyToOne
	@JoinColumn(name = "fk_user_void")
	private User user_void;
	private LocalDateTime void_date;

	public User getUser_void() {
		return user_void;
	}

	public void setUser_void(User user_void) {
		this.user_void = user_void;
	}

	public LocalDateTime getVoid_date() {
		return void_date;
	}

	public void setVoid_date(LocalDateTime void_date) {
		this.void_date = void_date;
	}

	public boolean isReceipt_void() {
		return receipt_void;
	}

	public void setReceipt_void(boolean receipt_void) {
		this.receipt_void = receipt_void;
	}

 

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
 

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser_casher() {
		return user_casher;
	}

	public void setUser_casher(User user_casher) {
		this.user_casher = user_casher;
	}
 
	
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	 

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	@Override
	public String toString() {
		return "DepositReciept [id=" + id + ", user=" + user.getFullName() + ", description=" + description + ", receiptNo="
				  + ", amount=" + amount + ", receipt_void=" + receipt_void + ", created_date=" +", user_casher=" + user_casher + ", user_void=" + user_void + ", void_date=" + void_date + "]";
	}
	
	
	

}