package com.justmehr.backend.domain.dto;

import java.time.LocalDateTime;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.justmehr.backend.domain.Customer;
import com.justmehr.backend.domain.Trainer;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CourseDTO {
	
	Long id;
	
	@NotBlank
	String name;
	
	int courseDuration;
	LocalDateTime startDateTime;
	String courseType;
	String status;		
	
	@JsonIgnore
	Trainer trainer;
	Customer customer;

}
