package com.ninad.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ninad.dao.entity.Stage;
import com.ninad.dao.entity.repo.StageRepo;
import com.ninad.to.ProjectDetailsTO;
import com.ninad.to.StageTO;

@RestController
//@CrossOrigin(origins = "http://localhost:4200") 
@RequestMapping("/api/v1/stage")
public class StageController {

	@Autowired
	StageRepo stageRepository;
	
	@GetMapping
	public List<StageTO>  getAllStages(@RequestParam(required = false, defaultValue = "false") boolean mainOnly) {


	    List<Stage> stages;

	    if (mainOnly) {
	        // Fetch only top-level stages (those without a parent)
	        stages = stageRepository.findByParentStageIsNull();
	    } else {
	        // Fetch all stages
	        stages = stageRepository.findAll();
	    }

	    return stages.stream()
	                 .map(this::convertToTO)
	                 .collect(Collectors.toList());
    }

	

    @PutMapping("/{id}/payment")
    public ResponseEntity<?> updatePaymentPercentage(
            @PathVariable Long id,
            @RequestBody StageTO stageTO) {
        
        Stage stage = stageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stage not found"));
        stage.setPaymentPercentage(stageTO.getPaymentPercentage());
        stageRepository.save(stage);
        return ResponseEntity.ok("Updated payment percentage successfully");
    }
	
    private StageTO convertToTO(Stage stage) {
        StageTO to = new StageTO();
        to.setId(stage.getId());
        to.setParentid(stage.getParentStage() != null ? stage.getParentStage().getId() : 0);
        to.setParentStageName(stage.getParentStage() != null ? stage.getParentStage().getName() : null);
        to.setName(stage.getName());
        to.setDescription(stage.getDescription());
        to.setOrder(stage.getOrderIndex());
        to.setPaymentPercentage(stage.getPaymentPercentage());
        return to;
    }
}
