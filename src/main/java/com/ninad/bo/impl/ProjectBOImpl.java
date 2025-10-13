package com.ninad.bo.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.ninad.bo.ProjectBO;
import com.ninad.dao.entity.BlueprintDetails;
import com.ninad.dao.entity.LandDetails;
import com.ninad.dao.entity.Project;
import com.ninad.dao.entity.ProjectStageProgression;
import com.ninad.dao.entity.Stage;
import com.ninad.dao.entity.User;
import com.ninad.dao.entity.repo.BlueprintDetailsRepo;
import com.ninad.dao.entity.repo.LandDetailsRepo;
import com.ninad.dao.entity.repo.ProjectRepo;
import com.ninad.dao.entity.repo.StageRepo;
import com.ninad.service.StatusService;
import com.ninad.to.BlueprintDetailsTO;
import com.ninad.to.LandDetailsTO;
import com.ninad.to.ProjectDetailsTO;
import com.ninad.to.StageTO;

import jakarta.transaction.Transactional;

@Service
public class ProjectBOImpl implements ProjectBO {
	
	@Autowired
	ProjectRepo projectdetailsRepo;
	

	@Autowired
	LandDetailsRepo landDetailsRepo;
	
	@Autowired
	BlueprintDetailsRepo blueprintDetailsRepo;
	
	@Autowired
	StageRepo stageRepo;
	
	@Autowired
	StatusService statusService;
	
	@Autowired
	com.ninad.dao.entity.repo.ProjectStageProgressionRepository ProjectStageProgressionRepository;
	
	
	@Override
	@Transactional
	public ProjectDetailsTO createProject(ProjectDetailsTO projectDetailsTO) {

		   // 1️⃣ Create LandDetails entity
	   
	    // 3️⃣ Create Project entity
	    Project project = new Project();
	    project.setTitle(projectDetailsTO.getTitle());
	    project.setOverview(projectDetailsTO.getOverview());
	    project.setLocation(projectDetailsTO.getLocation());
	    project.setProjectCost(projectDetailsTO.getProjectCost());
	    project.setEstimatedCost(projectDetailsTO.getEstimatedCost());
	    //project.setCurrentStage(null);

	    
	    LandDetails landDetails = new LandDetails();
	    if (projectDetailsTO.getLandDetails() != null) {
	        LandDetailsTO landTO = projectDetailsTO.getLandDetails();
	        landDetails.setZone(landTO.getZone());
	        landDetails.setUbl(landTO.getUbl());
	        landDetails.setPlotNo(landTO.getPlotNo());
	        landDetails.setLandType(landTO.getLandType());
	        landDetails.setAreaType(landTO.getAreaType());
	        landDetails.setFsi(landTO.getFsi());
	       
	    }

	    // 2️⃣ Create BlueprintDetails entity
	    BlueprintDetails blueprintDetails = new BlueprintDetails();
	    if (projectDetailsTO.getBlueprintDetails() != null) {
	        BlueprintDetailsTO blueprintTO = projectDetailsTO.getBlueprintDetails();
	        blueprintDetails.setNationalizedBankLoad(blueprintTO.isNationalizedBankLoan());
	        blueprintDetails.setPmayScheme(blueprintTO.isPmayScheme());
	        blueprintDetails.setType(blueprintTO.getType());
	        
	    }

	    // 4️⃣ Set LandDetails and BlueprintDetails
	    project.setLandDetails(landDetails);
	    project.setBlueprintDetails(blueprintDetails);
	    Stage stage = new Stage();
	    stage.setId(1l);
	    project.setCurrentStage(stage);
	    User user = new User();
	    user.setId(projectDetailsTO.getUserTO().getId());
	    project.setUser(user);
	    project.setStatus(statusService.getStatusByCode("IN_PROGRESS").get());
	    // 5️⃣ Save Project (cascades LandDetails and BlueprintDetails)
	    Project savedProject = projectdetailsRepo.save(project);
	    
	    // create first progression
	    ProjectStageProgression initialProgression = new ProjectStageProgression();
	    initialProgression.setProject(savedProject);
	    initialProgression.setStage(stage);
	    initialProgression.setStatus(statusService.getStatusByCode("IN_PROGRESS").get());
	    initialProgression.setCreatedBy("System");
	    initialProgression.setStartedOn(LocalDateTime.now());

	    ProjectStageProgressionRepository.save(initialProgression);

	    projectdetailsRepo.save(savedProject);
	    
	    
	    return mapToTO(savedProject);
	}


	@Override
	public List<ProjectDetailsTO> getAllProjects() {
	    return projectdetailsRepo.findAll().stream()
	            .map(project -> {
	                ProjectDetailsTO to = new ProjectDetailsTO();
	                to.setId(project.getId());
	                to.setTitle(project.getTitle());
	                to.setOverview(project.getOverview());
	                to.setLocation(project.getLocation());
	                to.setEstimatedCost(project.getEstimatedCost());
	                Stage stage = project.getCurrentStage();
	                if (stage != null) {
	                    StageTO stageTO = new StageTO();
	                    stageTO.setId(stage.getId());
	                    stageTO.setName(stage.getName());
	                    stageTO.setDescriptop(stage.getDescription());
	                    stageTO.setOrder(stage.getOrderIndex());

	                    if (stage.getParentStage() != null) {
	                        stageTO.setParentid(stage.getParentStage().getId());
	                        stageTO.setParentStageName(stage.getParentStage().getName());
	                    }

	                    to.setStage(stageTO);
	                } else {
	                    to.setStage(null);
	                }

	                // Map LandDetails
	                if (project.getLandDetails() != null) {
	                    LandDetailsTO landTO = new LandDetailsTO();
	                    landTO.setZone(project.getLandDetails().getZone());
	                    landTO.setUbl(project.getLandDetails().getUbl());
	                    landTO.setPlotNo(project.getLandDetails().getPlotNo());
	                    landTO.setLandType(project.getLandDetails().getLandType());
	                    landTO.setAreaType(project.getLandDetails().getAreaType());
	                    landTO.setFsi(project.getLandDetails().getFsi());
	                    to.setLandDetails(landTO);
	                }

	                // Map BlueprintDetails
	                if (project.getBlueprintDetails() != null) {
	                    BlueprintDetailsTO bpTO = new BlueprintDetailsTO();
	                    bpTO.setNationalizedBankLoan(project.getBlueprintDetails().isNationalizedBankLoad());
	                    bpTO.setPmayScheme(project.getBlueprintDetails().isPmayScheme());
	                    bpTO.setType(project.getBlueprintDetails().getType());
	                    to.setBlueprintDetails(bpTO);
	                }

	                return to;
	            })
	            .collect(Collectors.toList());
	}
	   @Override
	    public ProjectDetailsTO getProjectById(int id) {
	        return mapToTO(projectdetailsRepo.findById(id));

	    }

	    // Helper mapping method
	    private ProjectDetailsTO mapToTO(Project project) {
	        ProjectDetailsTO to = new ProjectDetailsTO();
	        to.setId(project.getId());
	        to.setTitle(project.getTitle());
	        to.setOverview(project.getOverview());
	        to.setLocation(project.getLocation());
	        to.setProjectCost(project.getProjectCost());
	        to.setEstimatedCost(project.getEstimatedCost());
	        Stage stage = project.getCurrentStage();
            if (stage != null) {
                StageTO stageTO = new StageTO();
                stageTO.setId(stage.getId());
                stageTO.setName(stage.getName());
                stageTO.setDescriptop(stage.getDescription());
                stageTO.setOrder(stage.getOrderIndex());

                if (stage.getParentStage() != null) {
                    stageTO.setParentid(stage.getParentStage().getId());
                    stageTO.setParentStageName(stage.getParentStage().getName());
                }

                to.setStage(stageTO);
            } else {
                to.setStage(null);
            }
	     // Map LandDetails
	        LandDetails det = project.getLandDetails();
	        if (det != null) {
	            LandDetailsTO detTo = new LandDetailsTO();
	            detTo.setAreaType(det.getAreaType());
	            detTo.setFsi(det.getFsi());
	            detTo.setLandType(det.getLandType());
	            detTo.setUbl(det.getUbl());
	            detTo.setZone(det.getZone());
	            detTo.setPlotNo(det.getPlotNo());
	            to.setLandDetails(detTo);
	        }

	        // Map BlueprintDetails
	        BlueprintDetails blueprint = project.getBlueprintDetails();
	        if (blueprint != null) {
	            BlueprintDetailsTO blueprintTO = new BlueprintDetailsTO();
	            blueprintTO.setNationalizedBankLoan(blueprint.isNationalizedBankLoad());
	            blueprintTO.setPmayScheme(blueprint.isPmayScheme());
	            blueprintTO.setType(blueprint.getType());
	            to.setBlueprintDetails(blueprintTO);
	        }
	        return to;
	    }


		@Override
	    public ResponseEntity<ProjectDetailsTO> updateProject(@PathVariable int id,@RequestBody ProjectDetailsTO projectTO) {
			Project project = projectdetailsRepo.findById(id);
			System.out.println(" updating proj");
	        // Update root fields
	        project.setTitle(projectTO.getTitle());
	        project.setOverview(projectTO.getOverview());
	        project.setLocation(projectTO.getLocation());
	        project.setEstimatedCost(projectTO.getEstimatedCost());

	        // Update Land Details
	        if (projectTO.getLandDetails() != null) {
	            LandDetails land = project.getLandDetails();
	            if (land == null) {
	                land = new LandDetails();
	                project.setLandDetails(land);
	            }
	            land.setZone(projectTO.getLandDetails().getZone());
	            land.setUbl(projectTO.getLandDetails().getUbl());
	            land.setPlotNo(projectTO.getLandDetails().getPlotNo());
	            land.setLandType(projectTO.getLandDetails().getLandType());
	            land.setAreaType(projectTO.getLandDetails().getAreaType());
	            land.setFsi(projectTO.getLandDetails().getFsi());
	        }

	        // Update Blueprint Details
	        if (projectTO.getBlueprintDetails() != null) {
	            BlueprintDetails blueprint = project.getBlueprintDetails();
	            if (blueprint == null) {
	                blueprint = new BlueprintDetails();
	                project.setBlueprintDetails(blueprint);
	            }
	            blueprint.setNationalizedBankLoad(projectTO.getBlueprintDetails().isNationalizedBankLoan());
	            blueprint.setPmayScheme(projectTO.getBlueprintDetails().isPmayScheme());
	            blueprint.setType(projectTO.getBlueprintDetails().getType());
	           // blueprint.setProposalCode(projectTO.getBlueprintDetails().getProposalCode());
	        }

	        // Stage is usually updated separately (mark as complete, move to next etc.)
	        // But if you want to update it directly:
	        if (projectTO.getStage() != null) {
	            Stage stage = project.getCurrentStage();
	            if (stage == null) {
	                stage = new Stage();
	                project.setCurrentStage(stage);
	            }
	            stage.setName(projectTO.getStage().getName());
	           // stage.setParentStage(projectTO.getStage().getParentid());
	           // stage.setParentStageName(projectTO.getStage().getParentStageName());
	            //stage.setOrder(projectTO.getStage().getOrder());
	        }

	        Project updated = projectdetailsRepo.save(project);

	        // convert back to TO
	        ProjectDetailsTO responseTO = convertToTO(updated);

	        return ResponseEntity.ok(responseTO);
		}
		private ProjectDetailsTO convertToTO(Project project) {
	        ProjectDetailsTO to = new ProjectDetailsTO();
	        to.setId(project.getId());
	        to.setTitle(project.getTitle());
	        to.setOverview(project.getOverview());
	        to.setLocation(project.getLocation());
	        to.setEstimatedCost(project.getEstimatedCost());

	        if (project.getLandDetails() != null) {
	            LandDetailsTO landTO = new LandDetailsTO();
	            landTO.setZone(project.getLandDetails().getZone());
	            landTO.setUbl(project.getLandDetails().getUbl());
	            landTO.setPlotNo(project.getLandDetails().getPlotNo());
	            landTO.setLandType(project.getLandDetails().getLandType());
	            landTO.setAreaType(project.getLandDetails().getAreaType());
	            landTO.setFsi(project.getLandDetails().getFsi());
	            to.setLandDetails(landTO);
	        }

	        if (project.getBlueprintDetails() != null) {
	            BlueprintDetailsTO bpTO = new BlueprintDetailsTO();
	            bpTO.setNationalizedBankLoan(project.getBlueprintDetails().isNationalizedBankLoad());
	            bpTO.setPmayScheme(project.getBlueprintDetails().isPmayScheme());
	            bpTO.setType(project.getBlueprintDetails().getType());
	           // bpTO.setProposalCode(project.getBlueprintDetails().getProposalCode());
	            to.setBlueprintDetails(bpTO);
	        }

	        if (project.getCurrentStage() != null) {
	            StageTO stTO = new StageTO();
	            stTO.setId(project.getCurrentStage().getId());
	            stTO.setName(project.getCurrentStage().getName());
	            //stTO.setParentid(project.getCurrentStage().getParentid());
	           // stTO.setParentStageName(project.getCurrentStage().getParentStageName());
	           // stTO.setOrder(project.getCurrentStage().getOrder());
	            to.setStage(stTO);
	        }

	        return to;
	    }
}
