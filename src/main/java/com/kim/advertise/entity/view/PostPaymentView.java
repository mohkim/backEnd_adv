package com.kim.advertise.entity.view;

import com.kim.advertise.entity.Currency;
import com.kim.advertise.entity.post.EPostPaymentOptions;

public interface PostPaymentView {

  
	public EPostPaymentOptions getOption();
	
 
	public boolean isNegotiable();
	
	 
	public Integer getPrice_amount();
	 
	public Integer getMin();
	 
	public Integer getMax();
	
	public Currency getPrice_currency() ;
	  
	public Currency getRange_currency();
	 
}
