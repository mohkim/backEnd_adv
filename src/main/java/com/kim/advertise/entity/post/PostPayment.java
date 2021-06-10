package com.kim.advertise.entity.post;

import javax.persistence.*;

import com.kim.advertise.entity.Currency;
import com.kim.advertise.entity.emum.EPostPaymentOptions;

 

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
	    
	    @ManyToOne
	    private  Currency   price_currency;
	    
	    private  Integer   min;
	    private   Integer  max;
		@ManyToOne
        private  Currency   range_currency;
	    
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
		public Currency getPrice_currency() {
			return price_currency;
		}
		public void setPrice_currency(Currency price_currency) {
			this.price_currency = price_currency;
		}
		public Currency getRange_currency() {
			return range_currency;
		}
		public void setRange_currency(Currency range_currency) {
			this.range_currency = range_currency;
		}
	  
	    
}