package com.justmehr.backend.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@NotBlank
	String name;
	int courseDuration;
	LocalDateTime startDateTime;
	String courseType;
	String status;
	
	@OneToOne
	@JsonIgnore
	Trainer trainer;
	
	@ManyToOne
	@JoinColumn( name="customer_id" )
	@JsonBackReference
	Customer customer;
}
