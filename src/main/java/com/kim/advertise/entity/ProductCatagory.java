package com.kim.advertise.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

 

@Entity
@Table(name = "product_category")

public class ProductCatagory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	@NotBlank
	private String name;

 

	private String img;

	@OneToMany(fetch = FetchType.LAZY,
			mappedBy = "productCatagory",
			cascade = CascadeType.ALL, orphanRemoval = true)
	 
	List<ProductSubCatagory> productSubCatagory = new ArrayList<ProductSubCatagory>();

 
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



	public List<ProductSubCatagory> getProductSubcatagory() {
		return productSubCatagory;
	}

	public void remoProductSubcatagory(ProductSubCatagory proSubCat) {
		productSubCatagory.remove(proSubCat);
	}

	public boolean addProductSubcatagory(ProductSubCatagory proSubCat) {
		return productSubCatagory.add(proSubCat);
	}

	public void setProductSubcatagory(List<ProductSubCatagory> productSubCatagory) {
		this.productSubCatagory = productSubCatagory;
	}
 

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public List<ProductSubCatagory> getProductSubCatagory() {
		return productSubCatagory;
	}

	public void setProductSubCatagory(List<ProductSubCatagory> productSubCatagory) {
		this.productSubCatagory = productSubCatagory;
	}

	public ProductCatagory(@NotBlank String name) {
		super();
		this.name = name;
	}

	public ProductCatagory() {

	}

	@Override
	public String toString() {
		return "ProductCatagory [id=" + id + ", name=" + name +  ", img=" + img
				+ ", productSubCatagory=" + productSubCatagory + "]";
	}

	 
}
