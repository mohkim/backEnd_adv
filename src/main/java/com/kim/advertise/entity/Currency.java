package com.kim.advertise.entity;

import javax.persistence.*;

@Entity
 
public class Currency {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

 
	private String longName;
	private String  shortName;
	
	
	
	public Currency(String longName, String shortName) {
		super();
		this.longName = longName;
		this.shortName = shortName;
	}


	public Currency() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getLongName() {
		return longName;
	}


	public void setLongName(String longName) {
		this.longName = longName;
	}


	public String getShortName() {
		return shortName;
	}


	public void setShortName(String shortName) {
		this.shortName = shortName;
	}


	 

	 
}