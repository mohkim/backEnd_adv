package com.kim.advertise.entity;

import javax.persistence.*;

 

@Entity
 
public class PostPayment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
    private EPostPaymentOptions   option;
	 
	    
	    private  boolean   negotiable;
	    private  Integer  price_amount;
	    private  Integer   min;
	    private   Integer  max;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public EPostPaymentOptions getOption() {
			return option;
		}
		public void setOption(EPostPaymentOptions option) {
			this.option = option;
		}
		public boolean isNegotiable() {
			return negotiable;
		}
		public void setNegotiable(boolean negotiable) {
			this.negotiable = negotiable;
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
	  
	    
}