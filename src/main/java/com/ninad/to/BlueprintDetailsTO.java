package com.ninad.to;

public class BlueprintDetailsTO {
	
	boolean nationalizedBankLoan;
	
	
	boolean pmayScheme;
	
	String type;


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
