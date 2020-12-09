package com.kim.advertise.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.sun.istack.NotNull;

@Entity
@Table(name = "POST")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "fk_user")
	private User user;
    private Integer view;

	
	private String description;
	

	
	@Column(length = 10485760)
	private String detail;
    
	@NotNull
    private Integer price;
      
 
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "fk_sales_location")
	private  SalesLocation  salesLocation;
	
	@OneToOne
	@JoinColumn(name = "fk_subCatagory")
	private ProductSubCatagory productSubCatagory;
	 
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "fk_post")
	private List<PictureUpload> postImage = new ArrayList<PictureUpload>();
    
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "fk_post")
	private List<PostSpecification> specificationList = new ArrayList<PostSpecification>();
	@CreationTimestamp
	private LocalDateTime createdDate;

	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "fk_payment")
	private  PostPayment  post_payment;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Integer getView() {
		return view;
	}


	public void setView(Integer view) {
		this.view = view;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getDetail() {
		return detail;
	}


	public void setDetail(String detail) {
		this.detail = detail;
	}


	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}


 


	public SalesLocation getSalesLocation() {
		return salesLocation;
	}


	public void setSalesLocation(SalesLocation salesLocation) {
		this.salesLocation = salesLocation;
	}


	public ProductSubCatagory getProductSubCatagory() {
		return productSubCatagory;
	}


	public void setProductSubCatagory(ProductSubCatagory productSubCatagory) {
		this.productSubCatagory = productSubCatagory;
	}


	public List<PictureUpload> getPostImage() {
		return postImage;
	}


	public void setPostImage(List<PictureUpload> postImage) {
		this.postImage = postImage;
	}
	
	public void addPostImage(PictureUpload p) {
		this.postImage.add(p);
	}
	public void removePostImage(PictureUpload p) {
		this.postImage.remove(p);
	}

	public List<PostSpecification> getSpecificationList() {
		return specificationList;
	}
	public void addSpecification(PostSpecification s) {
		this.specificationList.add(s);
	}
	public void removeSpecification(PostSpecification s) {
		this.specificationList.remove(s);
	}

	public void setSpecificationList(List<PostSpecification> specificationList) {
		this.specificationList = specificationList;
	}


	public LocalDateTime getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}


	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}


	public PostPayment getPost_payment() {
		return post_payment;
	}


	public void setPost_payment(PostPayment post_payment) {
		this.post_payment = post_payment;
	}
	 
}