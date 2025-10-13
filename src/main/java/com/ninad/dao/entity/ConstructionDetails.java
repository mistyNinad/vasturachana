package com.ninad.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ConstructionDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	int id;
}
