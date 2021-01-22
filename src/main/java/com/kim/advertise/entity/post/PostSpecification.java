package com.kim.advertise.entity.post;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kim.advertise.entity.SpecificationHead;

 

@Entity
@Table(name = "Post_Specification")
public class PostSpecification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
     
	 @OneToOne
	 private  SpecificationHead   specification;
	 
	 private  String  value;
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	 
	
	public SpecificationHead getSpecification() {
		return specification;
	}
	public void setSpecification(SpecificationHead specification) {
		this.specification = specification;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	 
	 
}