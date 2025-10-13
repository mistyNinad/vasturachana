package com.ninad.dao.entity.repo;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ninad.dao.entity.Project;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Integer>{

	Project findById(int id);

}
