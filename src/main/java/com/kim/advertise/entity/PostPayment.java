package com.kim.advertise.entity;

import javax.persistence.*;

 

@Entity
 
public class PostPayment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    
	    private  boolean   contact;
	    private  boolean   price;
	    private  boolean   negotiable;
	    private  boolean   commision;
	    private  boolean   range;
	    
	    
	    
	    private  Integer  price_amount;
	    private  Integer   min;
	    private   Integer  max;
	    
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public boolean isContact() {
			return contact;
		}
		public void setContact(boolean contact) {
			this.contact = contact;
		}
		public boolean isPrice() {
			return price;
		}
		public void setPrice(boolean price) {
			this.price = price;
		}
		public boolean isCommision() {
			return commision;
		}
		public void setCommision(boolean commision) {
			this.commision = commision;
		}
		public boolean isRange() {
			return range;
		}
		public void setRange(boolean range) {
			this.range = range;
		}
		public Integer getPrice_amount() {
			return price_amount;
		}
		public void setPrice_amount(Integer price_amount) {
			this.price_amount = price_amount;
		}
		public Integer getMin() {
			return min;
		}
		public void setMin(Integer min) {
			this.min = min;
		}
		public Integer getMax() {
			return max;
		}
		public void setMax(Integer max) {
			this.max = max;
		}
		public boolean isNegotiable() {
			return negotiable;
		}
		public void setNegotiable(boolean negotiable) {
			this.negotiable = negotiable;
		}
	    
	    
	    
}