package com.justmehr.backend.domain;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity(name="customer")
public class Customer extends User{	
	
		
	@OneToMany	
	Set<Course> courses;	

}
