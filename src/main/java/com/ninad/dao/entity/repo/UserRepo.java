package com.ninad.dao.entity.repo;
import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.ninad.dao.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

	User findByUsername(String username);

	Optional<User> findByMobileNumber(String bigDecimal);


	Optional<User> findByUsernameAndPassword(String userName, String password);

}
