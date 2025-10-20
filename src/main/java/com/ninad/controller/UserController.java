package com.ninad.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ninad.dao.entity.Role;
import com.ninad.dao.entity.User;
import com.ninad.dao.entity.UserDetails;
import com.ninad.dao.entity.repo.UserDetailsRepo;
import com.ninad.dao.entity.repo.UserRepo;
import com.ninad.to.UserTO;



@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:4200") 
public class UserController {


@Autowired
UserRepo userRepo;
	

@Autowired
private UserDetailsRepo userDetailsRepo;
	

@PostMapping
public ResponseEntity<?> saveUserInDB(@RequestBody UserTO userTO) {

    // Save core User with new fields
    User userEntity = new User();
    userEntity.setName(userTO.getName());
    userEntity.setPassword(userTO.getPassword());
    userEntity.setUsername(userTO.getMobileNumber()); // using mobile as username
    userEntity.setMobileNumber(userTO.getMobileNumber());
    userEntity.setAdharNumber(userTO.getAdharNumber());
    userEntity.setPanNumber(userTO.getPanNumber());
    userEntity.setStatus(true); // default active
    Role r = new Role();
    r.setId(3);
    userEntity.setRole(r);
    // Assign role if provided
//    if (userTO.getRoleId() != null) {
//        Role role = roleRepo.findById(userTO.getRoleId())
//                            .orElseThrow(() -> new RuntimeException("Role not found"));
//        userEntity.setRole(role);
//    }

    User savedUser = userRepo.save(userEntity);

    // Save UserDetails (email, address, notes, whatsapp)
    UserDetails details = new UserDetails();
    details.setUser(savedUser);
    details.setEmail(userTO.getEmail());
    details.setAddress(userTO.getAddress());
    details.setNotes(userTO.getNotes());
    if (userTO.getWhatsappNumber() != null) {
        details.setWhatsappNumber(new java.math.BigDecimal(userTO.getWhatsappNumber()));
    }

    userDetailsRepo.save(details);

    return ResponseEntity.ok(savedUser);
}


@GetMapping
 public List<UserTO> getUsers(){
	 
    List<User> users = userRepo.findAll();
    List<UserTO> result = new ArrayList<>();

    for (User user : users) {
        UserTO to = new UserTO();
        to.setId(user.getId());
        to.setName(user.getName());
        to.setUsername(user.getUsername());
        to.setPassword(user.getPassword());
        to.setAdharNumber(user.getAdharNumber());
        to.setPanNumber(user.getPanNumber());
        to.setMobileNumber(user.getMobileNumber());

        // fetch details if exists
        UserDetails details = user.getUserDetails();
        if (details != null) {
            to.setEmail(details.getEmail());
            to.setAddress(details.getAddress());
            to.setNotes(details.getNotes());
           
            if (details.getWhatsappNumber() != null) to.setWhatsappNumber(details.getWhatsappNumber().toString());
        }

        result.add(to);
    }

    return result;

	
 }
@GetMapping("/find")
public User getUser(@RequestParam String username){
	return userRepo.findByUsername(username);
	 
	
}

// Get user by id
@GetMapping("/{id}")
public UserTO getUserById(@PathVariable int id) {
	User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
	UserTO to = new UserTO();
    to.setId(user.getId());
    to.setName(user.getName());
    to.setUsername(user.getUsername());
    to.setPassword(user.getPassword());
    to.setAdharNumber(user.getAdharNumber());
    to.setPanNumber(user.getPanNumber());

    // fetch details if exists
    UserDetails details = user.getUserDetails();
    if (details != null) {
        to.setEmail(details.getEmail());
        to.setAddress(details.getAddress());
        to.setNotes(details.getNotes());
       
        if (details.getWhatsappNumber() != null) to.setWhatsappNumber(details.getWhatsappNumber().toString());
    }
    return to;
}

@PutMapping("/{id}")
public User updateUser(@PathVariable int id, @RequestBody User userDetails) {
    User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    user.setName(userDetails.getName());
    user.setUsername(userDetails.getUsername());
    user.setPassword(userDetails.getPassword());
    return userRepo.save(user);
}

// Delete user
@DeleteMapping("/{id}")
public String deleteUser(@PathVariable int id) {
    userRepo.deleteById(id);
    return "User deleted successfully";
}

// GET /user/search?mobile=9876543210
@GetMapping("/search")
public ResponseEntity<UserTO> getUserByMobile(@RequestParam String mobile) {
	System.out.println("searching user ");
    Optional<User> user = userRepo.findByMobileNumber(mobile);
   	
	UserTO to = new UserTO();
    if (user.isPresent()) {
 
        to.setId(user.get().getId());
        to.setName(user.get().getName());
        to.setUsername(user.get().getUsername());
        to.setPassword(user.get().getPassword());
        to.setAdharNumber(user.get().getAdharNumber());
        to.setPanNumber(user.get().getPanNumber());
    }
        return ResponseEntity.ok(to);
}

}
