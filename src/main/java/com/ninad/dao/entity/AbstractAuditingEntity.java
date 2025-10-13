package com.ninad.dao.entity;

import java.io.Serializable;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractAuditingEntity implements Serializable {

	
	private static final long serialVersionUID = 5493994851991910240L;


	@CreatedBy
	@Column(name = "created_by")
	private String createdBy;
	
	
	@CreatedDate
	@Column(name = "created_on")
	private String createdOn;
	
	
	@LastModifiedBy
	@Column(name = "updated_by")
	private String updatedBy;
	
	
	
	@LastModifiedDate
	@Column(name = "updated_on")
	private String updatedOn;



	public String getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}



	public String getCreatedOn() {
		return createdOn;
	}



	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}



	public String getUpdatedBy() {
		return updatedBy;
	}



	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}



	public String getUpdatedOn() {
		return updatedOn;
	}



	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
