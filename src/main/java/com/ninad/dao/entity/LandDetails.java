package com.ninad.dao.entity;

import com.ninad.to.LandDetailsTO;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class LandDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	int id;
	
	String zone;
	
	String ubl;
	
	String plotNo;
	
	String landType;
	
	String areaType;
	
	double fsi;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getUbl() {
		return ubl;
	}

	public void setUbl(String ubl) {
		this.ubl = ubl;
	}

	public String getPlotNo() {
		return plotNo;
	}

	public void setPlotNo(String plotNo) {
		this.plotNo = plotNo;
	}

	public String getLandType() {
		return landType;
	}

	public void setLandType(String landType) {
		this.landType = landType;
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public double getFsi() {
		return fsi;
	}

	public void setFsi(double fsi) {
		this.fsi = fsi;
	}



}
