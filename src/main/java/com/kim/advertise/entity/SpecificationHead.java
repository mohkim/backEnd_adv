package com.kim.advertise.entity;


 

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

 
@Entity
public class SpecificationHead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     
    private String value;
    private String key;
    private String label;
    private boolean required;
    @Column(name="ord")
    private Integer order;
    private String controlType;
    private String type;
    public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	private String pattern;
    private String placeholder;
 
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
        )
    @JoinColumn(name = "fk_specificationHead" )
    private List<SpecificationHeadOption> options=new ArrayList<SpecificationHeadOption>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	 
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public String getControlType() {
		return controlType;
	}
	public void setControlType(String controlType) {
		this.controlType = controlType;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public List<SpecificationHeadOption> getOption() {
		return options;
	}
	public void setOption(List<SpecificationHeadOption> option) {
		this.options = option;
	}
	public void addOption(SpecificationHeadOption option) {
		this.options.add(option);
	}
 
	public void removeOption(SpecificationHeadOption option) {
		this.options.remove(option);
	}
	public String getPlaceholder() {
		return placeholder;
	}
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
	@Override
	public String toString() {
		return "SpecificationHead [label=" + label + ", required=" + required + ", order=" + order + ", controlType="
				+ controlType + ", type=" + type + ", pattern=" + pattern + ", placeholder=" + placeholder
				+ ", options=" + options + "]";
	}
	 
	
}
