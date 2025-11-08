package com.ninad.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ninad.dao.entity.User;
import com.ninad.dao.entity.repo.UserRepo;

@RestController
@RequestMapping("/api/v1")
//@CrossOrigin(origins = "http://localhost:4200") 
public class LoginController {


@Autowired
UserRepo userRepo;
	
	@GetMapping("/login")
	public ResponseEntity<?> login(@RequestParam String userName, @RequestParam String password) {
		System.out.print("inside login "+ password);
        Optional<User> userOpt = userRepo.findByUsernameAndPassword(userName, password);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            
            // For now, return basic user info
            Map<String, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("name", user.getName());
            response.put("mobileNumber", user.getMobileNumber());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
