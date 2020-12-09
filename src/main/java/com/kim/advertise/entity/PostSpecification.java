package com.kim.advertise.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.Specification;

@Entity
@Table(name = "Post_Specification")
public class PostSpecification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	 private  Specification<?>  specification;
	 private  String  value;
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Specification<?> getSpecification() {
		return specification;
	}
	public void setSpecification(Specification<?> specification) {
		this.specification = specification;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	 
	 
}