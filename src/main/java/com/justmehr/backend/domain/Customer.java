package com.justmehr.backend.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name="customer")
public class Customer extends User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;	
	
	@OneToMany	
	Set<Course> courses;	

}
