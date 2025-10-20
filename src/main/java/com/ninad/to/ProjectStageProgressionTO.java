package com.ninad.to;

import java.time.LocalDateTime;

public class ProjectStageProgressionTO {

	
	 private int id;
	    private StageTO stage;
	    private String status;
	    private LocalDateTime startedOn;
	    private LocalDateTime completedOn;
	    private String remarks;
	    private String completedBy;
	    
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
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public LocalDateTime getStartedOn() {
			return startedOn;
		}
		public void setStartedOn(LocalDateTime startedOn) {
			this.startedOn = startedOn;
		}
		public LocalDateTime getCompletedOn() {
			return completedOn;
		}
		public void setCompletedOn(LocalDateTime completedOn) {
			this.completedOn = completedOn;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public String getCompletedBy() {
			return completedBy;
		}
		public void setCompletedBy(String completedBy) {
			this.completedBy = completedBy;
		}
	 
}
