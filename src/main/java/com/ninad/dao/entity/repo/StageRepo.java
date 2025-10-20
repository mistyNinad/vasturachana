package com.ninad.dao.entity.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ninad.dao.entity.ProjectStageProgression;
import com.ninad.dao.entity.Stage;

@Repository
public interface StageRepo extends JpaRepository<Stage,Long> {
	
	  // For sub-stages of a parent
    Optional<Stage> findFirstByParentStageAndOrderIndexGreaterThanOrderByOrderIndexAsc(Stage parent, int orderIndex);

    // First sub-stage of a parent
    Optional<Stage> findFirstByParentStageOrderByOrderIndexAsc(Stage parent);

    // Next main stage
    Optional<Stage> findFirstByParentStageIsNullAndOrderIndexGreaterThanOrderByOrderIndexAsc(int orderIndex);

	List<Stage> findByParentStageIsNull();

}
