package com.ninad.dao.entity.repo;

import com.ninad.dao.entity.Project;
import com.ninad.dao.entity.ProjectPayment;
import com.ninad.dao.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectPaymentRepository extends JpaRepository<ProjectPayment, Integer> {
    List<ProjectPayment> findByProject(Project project);

    List<ProjectPayment> findByProjectId(int projectId);
}
