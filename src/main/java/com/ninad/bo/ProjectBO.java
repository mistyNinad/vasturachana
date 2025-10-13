package com.ninad.bo;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ninad.dao.entity.Project;
import com.ninad.to.ProjectDetailsTO;

public interface ProjectBO {

	public ProjectDetailsTO createProject(ProjectDetailsTO projectDetails);
	
	public List<ProjectDetailsTO> getAllProjects();
	
	 ProjectDetailsTO getProjectById(int id);

	 ResponseEntity<ProjectDetailsTO> updateProject(int id, ProjectDetailsTO projectTO);
}
