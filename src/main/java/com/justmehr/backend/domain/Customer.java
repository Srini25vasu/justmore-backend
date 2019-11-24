package com.justmehr.backend.domain;


import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name="customer")
@NamedEntityGraph(name = "Customer.courses", attributeNodes =  @NamedAttributeNode("courses"))
public class Customer extends User{		
		
	private static final long serialVersionUID = 53701511634555597L;
	
	@OneToMany(mappedBy = "customer",  cascade = CascadeType.ALL)	
	Set<Course> courses;

	public Optional<Set<Optional<Course>>> getCoursesOptional() {
		Set<Optional<Course>> optionalCourses = null;
		if(courses != null) {
			optionalCourses = courses.stream().map(course -> Optional.ofNullable(course)).collect(Collectors.toSet());
		}
		return Optional.ofNullable(optionalCourses);
	}
	
	public Set<Course> getCourses(){
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}	

}
