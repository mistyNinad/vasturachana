package com.ninad.dao.entity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ninad.dao.entity.Status;
import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Integer> {
	Optional<Status> findByCode(String code);
}
