package com.ninad.dao.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;// e.g., "Discussion", "Drawings"
    
    
    private int orderIndex;     // sequence of stage (1, 2, 3...)


    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Stage parentStage;  // for hierarchical sub-stages


    private String description;
    
    @OneToMany(mappedBy = "parentStage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stage> subStages = new ArrayList<>();


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getOrderIndex() {
		return orderIndex;
	}


	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}


	public Stage getParentStage() {
		return parentStage;
	}


	public void setParentStage(Stage parentStage) {
		this.parentStage = parentStage;
	}


	public String getStatus() {
		return description;
	}


	public void setStatus(String status) {
		this.description = status;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<Stage> getSubStages() {
		return subStages;
	}


	public void setSubStages(List<Stage> subStages) {
		this.subStages = subStages;
	}
    
    
}

