package com.ninad.to;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ProjectDetailsTO {

	int id;
	
	String title;
	
	String overview;
	
	String location;
	
	Double projectCost;
	
	Double estimatedCost;
	
	List<MultipartFile> images;
	
	LandDetailsTO landDetails;
	
	BlueprintDetailsTO blueprintDetails;
	
	StageTO stage;
	
	UserTO userTO;

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

	public List<MultipartFile> getImages() {
		return images;
	}

	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}

	public LandDetailsTO getLandDetails() {
		return landDetails;
	}

	public void setLandDetails(LandDetailsTO landDetails) {
		this.landDetails = landDetails;
	}

	public BlueprintDetailsTO getBlueprintDetails() {
		return blueprintDetails;
	}

	public void setBlueprintDetails(BlueprintDetailsTO blueprintDetails) {
		this.blueprintDetails = blueprintDetails;
	}

	public Double getProjectCost() {
		return projectCost;
	}

	public void setProjectCost(Double projectCost) {
		this.projectCost = projectCost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StageTO getStage() {
		return stage;
	}

	public void setStage(StageTO stage) {
		this.stage = stage;
	}

	public UserTO getUserTO() {
		return userTO;
	}

	public void setUserTO(UserTO userTO) {
		this.userTO = userTO;
	}
	
	
}
