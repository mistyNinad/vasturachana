package com.ninad.to;

public class StageTO {
 
	long id;
	long parentid;
	String parentStageName;
	String name;
	String descriptop;
	int order;
	
	

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getParentid() {
		return parentid;
	}
	public void setParentid(long parentid) {
		this.parentid = parentid;
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
	public String getDescriptop() {
		return descriptop;
	}
	public void setDescriptop(String descriptop) {
		this.descriptop = descriptop;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	
	
}
