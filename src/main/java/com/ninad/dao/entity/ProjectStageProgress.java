package com.ninad.dao.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "project_stage_progress")
public class ProjectStageProgress {

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "project_id")
	    private Project project;

	    @ManyToOne
	    @JoinColumn(name = "stage_id")
	    private Stage stage;

	    private String status; // PENDING, IN_PROGRESS, COMPLETED

	    private LocalDateTime startedAt;
	    
	    private LocalDateTime completedAt;
	    
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Project getProject() {
			return project;
		}
		public void setProject(Project project) {
			this.project = project;
		}
		public Stage getStage() {
			return stage;
		}
		public void setStage(Stage stage) {
			this.stage = stage;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public LocalDateTime getStartedAt() {
			return startedAt;
		}
		public void setStartedAt(LocalDateTime startedAt) {
			this.startedAt = startedAt;
		}
		public LocalDateTime getCompletedAt() {
			return completedAt;
		}
		public void setCompletedAt(LocalDateTime completedAt) {
			this.completedAt = completedAt;
		}
	    
	    
}
