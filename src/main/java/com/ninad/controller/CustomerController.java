package com.ninad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ninad.dao.entity.Customer;
import com.ninad.dao.entity.User;
import com.ninad.dao.entity.repo.CustomerRepo;
import com.ninad.dao.entity.repo.UserRepo;
import com.ninad.to.CustomerTO;
import com.ninad.to.UserTO;

@RestController
@RequestMapping("/customer")
public class CustomerController {


@Autowired
CustomerRepo customerRepo;
	
@PostMapping	
	public String saveCustomerInDB(@RequestBody CustomerTO customerTO) {
		
		Customer customerEntity = new Customer();
		customerEntity.setFirstname(customerTO.getFirstname());
		customerEntity.setLastname(customerTO.getLastname());
		customerEntity.setMobileno(customerTO.getMobileno());
		customerEntity.setWhatsappno(customerTO.getWhatsappno());
		
		Customer savedCustomer = customerRepo.save(customerEntity);
		
		return "Saved customer..."+ savedCustomer.getMobileno();
		
	}
}
