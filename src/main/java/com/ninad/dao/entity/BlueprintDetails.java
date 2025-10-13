package com.ninad.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class BlueprintDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	int id;
	
	@Column(name = "nationalized_bank_loan")
	boolean nationalizedBankLoan;
	
	@Column(name = "pmay_scheme")
	boolean pmayScheme;
	
	String type;
    

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isNationalizedBankLoad() {
		return nationalizedBankLoan;
	}

	public void setNationalizedBankLoad(boolean nationalizedBankLoad) {
		this.nationalizedBankLoan = nationalizedBankLoad;
	}

	public boolean isPmayScheme() {
		return pmayScheme;
	}

	public void setPmayScheme(boolean pmayScheme) {
		this.pmayScheme = pmayScheme;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isNationalizedBankLoan() {
		return nationalizedBankLoan;
	}

	public void setNationalizedBankLoan(boolean nationalizedBankLoan) {
		this.nationalizedBankLoan = nationalizedBankLoan;
	}

	
}
