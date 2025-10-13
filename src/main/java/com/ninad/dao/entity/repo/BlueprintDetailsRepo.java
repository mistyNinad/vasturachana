package com.ninad.dao.entity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ninad.dao.entity.BlueprintDetails;
import com.ninad.dao.entity.User;

@Repository
public interface BlueprintDetailsRepo extends JpaRepository<BlueprintDetails, Integer>{


}
