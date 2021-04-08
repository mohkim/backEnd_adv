package com.kim.advertise.form;

 
public class CatagoryByQuantity {

	private Long id;
	private String name;
	private String img;
	private Long qty;

	
	
	public CatagoryByQuantity(Long id, String name, String img, Long qty) {
		super();
		this.id = id;
		this.name = name;
		this.img = img;
		this.qty = qty;
	}

	
	public CatagoryByQuantity() {
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}


	public Long getQty() {
		return qty;
	}


	public void setQty(Long qty) {
		this.qty = qty;
	}


 
   

}