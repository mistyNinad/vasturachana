package com.ninad.dao.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	int id;
	
    String title;
	
	String overview;
	
	String location;
	
	@Column(name = "project_cost")
	Double projectCost;
	
	@Column(name = "estimated_cost")
	Double estimatedCost;
	
	@JoinColumn(name = "current_stage_id")
	@OneToOne
	Stage currentStage;
	

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status", nullable = false)
	Status status;
	
    @OneToOne(cascade = CascadeType.ALL)
    private LandDetails landDetails;

    @OneToOne(cascade = CascadeType.ALL)
    private BlueprintDetails blueprintDetails;

    //@OneToOne(cascade = CascadeType.ALL)
   // private ConstructionDetails constructionDetails;

	@JoinColumn(name = "user_id")
	@OneToOne
	User user;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Double getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(Double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	public Stage getCurrentStage() {
		return currentStage;
	}

	public void setCurrentStage(Stage currentStage) {
		this.currentStage = currentStage;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LandDetails getLandDetails() {
		return landDetails;
	}

	public void setLandDetails(LandDetails landDetails) {
		this.landDetails = landDetails;
	}

	public BlueprintDetails getBlueprintDetails() {
		return blueprintDetails;
	}

	public void setBlueprintDetails(BlueprintDetails blueprintDetails) {
		this.blueprintDetails = blueprintDetails;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getProjectCost() {
		return projectCost;
	}

	public void setProjectCost(Double projectCost) {
		this.projectCost = projectCost;
	}

	


}
