package com.kim.advertise.entity.post;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kim.advertise.entity.PictureUpload;
import com.kim.advertise.entity.ProductSubCatagory;
import com.kim.advertise.entity.SalesLocation;
import com.kim.advertise.entity.User;
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
	
	

	
 
	
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "fk_payment")
	private  PostPayment  post_payment;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "fk_receipt")
	private  PostRecipt   post_receipt;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "fk_post_status")
	private   Post_Status   post_status;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "fk_post_receipt")
    private   PostRecipt    receipt;


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

 
 

	public PostPayment getPost_payment() {
		return post_payment;
	}


	public void setPost_payment(PostPayment post_payment) {
		this.post_payment = post_payment;
	}


	public Post_Status getPost_status() {
		return post_status;
	}


	public void setPost_status(Post_Status post_status) {
		this.post_status = post_status;
	}


	public PostRecipt getPost_receipt() {
		return post_receipt;
	}


	public void setPost_receipt(PostRecipt post_receipt) {
		this.post_receipt = post_receipt;
	}


	public PostRecipt getReceipt() {
		return receipt;
	}


	public void setReceipt(PostRecipt receipt) {
		this.receipt = receipt;
	}


 
	 
}