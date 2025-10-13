package com.ninad.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	
    int id;
	
	String firstname;
	
	String lastname;
	
	Long mobileno;
	Long whatsappno;

	public Long getWhatsappno() {
		return whatsappno;
	}

	public void setWhatsappno(Long whatsappno) {
		this.whatsappno = whatsappno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Long getMobileno() {
		return mobileno;
	}

	public void setMobileno(Long long1) {
		this.mobileno = (long) long1;
	}
	


}
