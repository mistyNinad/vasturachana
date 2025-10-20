package com.ninad.dao.entity.repo;

import com.ninad.dao.entity.Project;
import com.ninad.dao.entity.ProjectStageProgression;
import com.ninad.dao.entity.Stage;
import com.ninad.dao.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectStageProgressionRepository extends JpaRepository<ProjectStageProgression, Integer> {

    List<ProjectStageProgression> findByProjectIdOrderByStartedOnAsc(int projectId);

    ProjectStageProgression findTopByProjectIdOrderByStartedOnDesc(int projectId); // last stage

	Optional<ProjectStageProgression> findByProjectAndStage(Project project, Stage currentStage);

	Optional<Project> findProjectById(int projectId);

	List<ProjectStageProgression> findByProjectIdAndStatusCode(int projectId, String string);

	List<ProjectStageProgression> findByProjectId(int projectId);
}