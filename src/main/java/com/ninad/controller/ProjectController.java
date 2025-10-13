package com.ninad.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ninad.bo.ProjectBO;
import com.ninad.dao.entity.Project;
import com.ninad.dao.entity.ProjectStageProgression;
import com.ninad.dao.entity.User;
import com.ninad.dao.entity.repo.BlueprintDetailsRepo;
import com.ninad.dao.entity.repo.LandDetailsRepo;
import com.ninad.dao.entity.repo.ProjectRepo;
import com.ninad.dao.entity.repo.StageRepo;
import com.ninad.dao.entity.repo.UserRepo;
import com.ninad.service.ProjectStageProgressionService;
import com.ninad.to.ProjectDetailsTO;
import com.ninad.to.ProjectStageProgressionTO;


@RestController
@RequestMapping("/api/v1/project")
@CrossOrigin(origins = "http://localhost:4200") 
public class ProjectController {


@Autowired
ProjectBO projectBO;

@Autowired
ProjectRepo projectdetailsRepo;


@Autowired
LandDetailsRepo landDetailsRepo;

@Autowired
BlueprintDetailsRepo blueprintDetailsRepo;

@Autowired
StageRepo stageRepo;

@Autowired
UserRepo userRepo;

@Autowired
ProjectStageProgressionService progressionService;
	
	
@PostMapping
public ProjectDetailsTO saveProjectDetails(@RequestBody ProjectDetailsTO projectdetailsTO) {

//    List<byte[]> imageBytes = new ArrayList<>();
//    for (MultipartFile file : projectdetailsTO.getImages()) {
//        imageBytes.add(file.getBytes());
//    }
	
    //project.setImages(imageBytes);
	return projectBO.createProject(projectdetailsTO);
	
	
}


@GetMapping("/projects")
public List<ProjectDetailsTO>  getAllProjects() {

return projectBO.getAllProjects();

}

@GetMapping("/{id}")
public ProjectDetailsTO getProjectById(@PathVariable int id) {
    return projectBO.getProjectById(id);
}

@PutMapping("/{id}")
public ResponseEntity<ProjectDetailsTO> updateProject(@PathVariable Integer id, @RequestBody ProjectDetailsTO updatedProject) {
	
	return projectBO.updateProject(id, updatedProject);
  
}

@PostMapping("/{id}/advance-stage")
public ResponseEntity<?> advanceStage(@PathVariable Integer id, @RequestBody Map<String, Integer> body) {
    Integer userId = body.get("userId");
    Project project = projectdetailsRepo.findById(id).orElseThrow();
    User user = userRepo.findById(userId).orElseThrow();
    ProjectStageProgressionTO next = progressionService.advanceStage(project, user);
    return ResponseEntity.ok(next);
}

}




