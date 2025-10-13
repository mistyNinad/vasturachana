package com.ninad.dao.entity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ninad.dao.entity.ConstructionDetails;
import com.ninad.dao.entity.User;

@Repository
public interface ConstructionDetailsRepo extends JpaRepository<ConstructionDetails, Integer>{

}
