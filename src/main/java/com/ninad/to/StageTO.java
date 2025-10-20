package com.ninad.to;

public class StageTO {
 
	long id;
	Long parentid;
	String parentStageName;
	String name;
	String description;
	int order;
	double paymentPercentage;
	

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getParentid() {
		return parentid;
	}

	public void setParentid(Long long1) {
		this.parentid = long1;
	}
	public String getParentStageName() {
		return parentStageName;
	}
	public void setParentStageName(String parentStageName) {
		this.parentStageName = parentStageName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPaymentPercentage() {
		return paymentPercentage;
	}
	public void setPaymentPercentage(double paymentPercentage) {
		this.paymentPercentage = paymentPercentage;
	}
	
	
}
