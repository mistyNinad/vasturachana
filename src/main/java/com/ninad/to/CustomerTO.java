package com.ninad.to;

public class CustomerTO {

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

	public void setMobileno(int mobileno) {
		this.mobileno = (long) mobileno;
	}
	

}
