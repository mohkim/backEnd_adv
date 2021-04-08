package com.kim.advertise.entity.view;

import java.util.ArrayList;

import java.util.List;

import com.kim.advertise.entity.PictureUpload;
 
 
public interface PostView {
 
	public Long getId();
   
	public UserView getUser();

    public Integer getView(); 

    public String getDescription();

    public String getDetail();
 
    public SalesLocationView getSalesLocation();

    public ProductSubCatagoryView getProductSubCatagory();

     public List<PictureUpload> getPostImage();

     public List<PostSpecificationView> getSpecificationList();
     
	 public PostPaymentView getPost_payment();

    
	public Post_StatusView getPost_status();
   
   


 
	 
}