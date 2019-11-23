package com.justmehr.backend.domain.dto;

import com.justmehr.backend.domain.Course;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TrainerDTO extends UserDTO{

	String teachingCourse;
	String professionType;
	Course course;

}
