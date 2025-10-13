package com.ninad.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ninad.dao.entity.Stage;
import com.ninad.dao.entity.repo.StageRepo;
import com.ninad.to.ProjectDetailsTO;
import com.ninad.to.StageTO;

@RestController
@CrossOrigin(origins = "http://localhost:4200") 
@RequestMapping("/api/v1/stage")
public class StageController {

	@Autowired
	StageRepo repo;
	
	@GetMapping("/stages")
	public List<StageTO>  getAllProjects() {

		   return repo.findAll()
                   .stream()
                   .map(this::convertToTO)
                   .collect(Collectors.toList());
    }

    private StageTO convertToTO(Stage stage) {
        StageTO to = new StageTO();
        to.setId(stage.getId());
        to.setParentid(stage.getParentStage() != null ? stage.getParentStage().getId() : 0);
        to.setParentStageName(stage.getParentStage() != null ? stage.getParentStage().getName() : null);
        to.setName(stage.getName());
        to.setDescriptop(stage.getDescription());
        to.setOrder(stage.getOrderIndex());
        return to;
    }
}
