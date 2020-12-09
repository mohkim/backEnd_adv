package com.kim.advertise.entity;


 

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


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
    
    @NotBlank
    private  String icon;
    
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
        )
    @JoinColumn(name = "fk_product" )
    List<ProductSubCatagory>   productSubCatagory=new ArrayList<ProductSubCatagory>();
    
    
   @CreationTimestamp
    public LocalDateTime createdDate;
    @UpdateTimestamp
    public LocalDateTime  updatedTime;
    
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

	
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<ProductSubCatagory> getProductSubcatagory() {
		return productSubCatagory;
	}
	public void remoProductSubcatagory(ProductSubCatagory  proSubCat) {
		     productSubCatagory.remove(proSubCat);
	}
	public boolean  addProductSubcatagory(ProductSubCatagory  proSubCat) {
		 return   productSubCatagory.add(proSubCat);
	}
	 

	public void setProductSubcatagory(List<ProductSubCatagory> productSubCatagory) {
		this.productSubCatagory = productSubCatagory;
	}

	 

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(LocalDateTime updatedTime) {
		this.updatedTime = updatedTime;
	}

	 
	 
	public ProductCatagory(@NotBlank String name) {
		super();
		this.name = name;
	}

	public ProductCatagory() {
		
	}

	@Override
	public String toString() {
		return "ProductCatagory [id=" + id + ", name=" + name + ", icon=" + icon + ", productSubCatagory="
				+ productSubCatagory + ", createdDate=" + createdDate + ", updatedTime=" + updatedTime + "]";
	}

    
}
