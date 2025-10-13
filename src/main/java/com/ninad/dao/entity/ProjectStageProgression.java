package com.ninad.dao.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "project_stage_progression")
public class ProjectStageProgression extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1970033901903137859L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // --- Relationships ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id", nullable = false)
    private Stage stage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @Column(length = 500)
    private String remarks;

    @Column(name = "started_on")
    private LocalDateTime startedOn;

    @Column(name = "completed_on")
    private LocalDateTime completedOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "completed_by")
    private User completedBy;

    // --- Getters & Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }

    public Stage getStage() { return stage; }
    public void setStage(Stage stage) { this.stage = stage; }



    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public LocalDateTime getStartedOn() { return startedOn; }
    public void setStartedOn(LocalDateTime startedOn) { this.startedOn = startedOn; }

    public LocalDateTime getCompletedOn() { return completedOn; }
    public void setCompletedOn(LocalDateTime completedOn) { this.completedOn = completedOn; }

    public User getCompletedBy() { return completedBy; }
    public void setCompletedBy(User completedBy) { this.completedBy = completedBy; }
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
    
    
}
