package com.ninad.service;


import com.ninad.dao.entity.Project;
import com.ninad.dao.entity.ProjectStageProgression;
import com.ninad.dao.entity.Stage;
import com.ninad.dao.entity.Status;
import com.ninad.dao.entity.User;
import com.ninad.dao.entity.repo.ProjectRepo;
import com.ninad.dao.entity.repo.ProjectStageProgressionRepository;
import com.ninad.dao.entity.repo.StageRepo;
import com.ninad.dao.entity.repo.StatusRepository;
import com.ninad.to.ProjectStageProgressionTO;
import com.ninad.to.StageTO;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.time.LocalDateTime;

@Service
public class ProjectStageProgressionService {

	 @Autowired
	    private StageRepo stageRepo;

	    @Autowired
	    private ProjectStageProgressionRepository progressionRepo;

	    @Autowired
	    private ProjectRepo projectRepo;
	    
	    @Autowired
	    private StatusService statusService;

	    public ProjectStageProgressionTO advanceStage(Project project, User completedBy) {
	        Stage currentStage = project.getCurrentStage();
	        ProjectStageProgression currentProgression = progressionRepo.findByProjectAndStage(project, currentStage)
	                .orElseThrow(() -> new RuntimeException("Current progression not found"));

	        // Complete current stage
	        currentProgression.setStatus(statusService.getStatusByCode("COMPLETED").get());
	        currentProgression.setCompletedBy(completedBy);
	        currentProgression.setCompletedOn(LocalDateTime.now());
	        progressionRepo.save(currentProgression);

	        // Determine next stage
	        Stage nextStage = null;

	        if (currentStage.getParentStage() != null) {
	            // current stage is a sub-stage
	            nextStage = stageRepo
	                    .findFirstByParentStageAndOrderIndexGreaterThanOrderByOrderIndexAsc(
	                            currentStage.getParentStage(), currentStage.getOrderIndex())
	                    .orElse(null);

	            if (nextStage == null) {
	                // no more sub-stages, move to next main stage
	                nextStage = stageRepo.findFirstByParentStageIsNullAndOrderIndexGreaterThanOrderByOrderIndexAsc(
	                        currentStage.getParentStage().getOrderIndex()).orElse(null);
	            }
	        } else {
	            // current stage is main stage
	            Optional<Stage> firstSubStage = stageRepo.findFirstByParentStageOrderByOrderIndexAsc(currentStage);
	            if (firstSubStage.isPresent()) {
	                nextStage = firstSubStage.get();
	            } else {
	                // move to next main stage
	                nextStage = stageRepo.findFirstByParentStageIsNullAndOrderIndexGreaterThanOrderByOrderIndexAsc(
	                        currentStage.getOrderIndex()).orElse(null);
	            }
	        }

	        if (nextStage == null) {
	            // project completed
	            project.setCurrentStage(null);
	            projectRepo.save(project);
	            return null;
	        }

	        // create next progression
	        ProjectStageProgression nextProgression = new ProjectStageProgression();
	        nextProgression.setProject(project);
	        nextProgression.setStage(nextStage);
	        nextProgression.setStatus(statusService.getStatusByCode("IN_PROGRESS").get());
	        nextProgression.setStartedOn(LocalDateTime.now());
	        progressionRepo.save(nextProgression);

	        // update project
	        project.setCurrentStage(nextStage);
	        projectRepo.save(project);
	        System.out.println(" updated proj with new stage, progression");
	        return mapToTO(nextProgression);
	    }
	    
	    @Transactional
	    public List<ProjectStageProgressionTO> findByProjectId(int projectId) {
	        return progressionRepo.findByProjectId(projectId)
	                .stream()
	                .map(this::mapToTO)
	                .toList();
	    }

	    private ProjectStageProgressionTO mapToTO(ProjectStageProgression progression) {
	        ProjectStageProgressionTO to = new ProjectStageProgressionTO();
	        to.setId(progression.getId());

	        // --- Stage mapping ---
	        Stage stage = progression.getStage();
	        if (stage != null) {
	            StageTO stageTO = new StageTO();
	            stageTO.setId(stage.getId());
	            stageTO.setParentid(stage.getParentStage() != null ? stage.getParentStage().getId() : null);
	            stageTO.setParentStageName(stage.getParentStage() != null ? stage.getParentStage().getName() : null);
	            stageTO.setName(stage.getName());
	            stageTO.setDescription(stage.getDescription());
	            stageTO.setOrder(stage.getOrderIndex());
	            to.setStage(stageTO);
	        }

	        // --- Progression info ---
	        if (progression.getStatus() != null) {
	            to.setStatus(progression.getStatus().getCode());
	        }

	        to.setStartedOn(progression.getStartedOn());
	        to.setCompletedOn(progression.getCompletedOn());
	        to.setRemarks(progression.getRemarks());

	        // --- Completed by user ---
	        if (progression.getCompletedBy() != null) {
	            to.setCompletedBy(progression.getCompletedBy().getName());
	        }

	        return to;
	    }

}