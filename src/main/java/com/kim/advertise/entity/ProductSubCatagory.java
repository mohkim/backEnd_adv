package com.kim.advertise.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ColumnDefault;

import com.sun.istack.NotNull;

@Entity

public class ProductSubCatagory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotBlank
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private EType type; // Product,Service,Job

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "fk_subCatagory")
	private List<SpecificationHead> specificationList = new ArrayList<SpecificationHead>();

	// properties of payment method
	@Column(name = "contact1")
	private boolean contact=false;
	@Column(name = "price1")
	private boolean price=false;
	@Column(name = "commsion1")
	@ColumnDefault(value="false")
	private boolean commision=false;
	@Column(name = "range1")
	private boolean range=false;
	// service charge and
	@Column(name = "fee_free_enable1")
	private boolean fee_free_enable=false;
	private Integer fee_free=0;
	@Column(name = "fee_week_enable1")
	private boolean fee_week_enable=false;
	private Integer fee_week;
	@Column(name = "fee_month_enable1")
	private boolean fee_month_enable=false;
	private Integer fee_month;
	@Column(name = "fee_year1_enable")
	private boolean fee_year_enable=false;;
	private Integer fee_year;
	@NotNull
	private  Integer  img_min=0;
	@NotNull
	private  Integer  img_max=0;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SpecificationHead> getSpecificationList() {
		return specificationList;
	}

	public void setSpecificationList(List<SpecificationHead> specificationList) {
		this.specificationList = specificationList;
	}

	public void addSpecificationList(SpecificationHead specification) {
		this.specificationList.add(specification);
	}

	public void removeSpecificationList(SpecificationHead specification) {
		this.specificationList.remove(specification);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EType getType() {
		return type;
	}

	public void setType(EType type) {
		this.type = type;
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

	public boolean isFee_week_enable() {
		return fee_week_enable;
	}

	public void setFee_week_enable(boolean fee_week_enable) {
		this.fee_week_enable = fee_week_enable;
	}

	public Integer getFee_week() {
		return fee_week;
	}

	public void setFee_week(Integer fee_week) {
		this.fee_week = fee_week;
	}

	public boolean isFee_month_enable() {
		return fee_month_enable;
	}

	public void setFee_month_enable(boolean fee_month_enable) {
		this.fee_month_enable = fee_month_enable;
	}

	public Integer getFee_month() {
		return fee_month;
	}

	public void setFee_month(Integer fee_month) {
		this.fee_month = fee_month;
	}

	public boolean isFee_year_enable() {
		return fee_year_enable;
	}

	public void setFee_year_enable(boolean fee_year_enable) {
		this.fee_year_enable = fee_year_enable;
	}

	public Integer getFee_year() {
		return fee_year;
	}

	public void setFee_year(Integer fee_year) {
		this.fee_year = fee_year;
	}

	public boolean isFee_free_enable() {
		return fee_free_enable;
	}

	public void setFee_free_enable(boolean fee_free_enable) {
		this.fee_free_enable = fee_free_enable;
	}

	public Integer getFee_free() {
		return fee_free;
	}

	public void setFee_free(Integer fee_free) {
		this.fee_free = fee_free;
	}

	public Integer getImg_min() {
		return img_min;
	}

	public void setImg_min(Integer img_min) {
		this.img_min = img_min;
	}

	public Integer getImg_max() {
		return img_max;
	}

	public void setImg_max(Integer img_max) {
		this.img_max = img_max;
	}
   
	
}
