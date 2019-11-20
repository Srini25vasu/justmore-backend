package com.justmehr.backend.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="trainer")
public class Trainer extends User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;	
	
	String teachingCourse;
	String profession;
	
	@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "course_id", referencedColumnName = "id")
	Course course;
}
