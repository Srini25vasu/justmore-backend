package com.justmehr.backend.rest;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.justmehr.backend.domain.Course;
import com.justmehr.backend.domain.dto.CourseDTO;
import com.justmehr.backend.exception.CourseCreationException;
import com.justmehr.backend.service.CourseService;
import com.justmehr.backend.util.MapperUtil;

@RestController
@RequestMapping("/api")
public class CourseResource {
	@Autowired
	CourseService courseService;

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	MapperUtil util;

	@PostMapping("/courses")
	public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CourseDTO courseDTO) {

		if (courseDTO.getId() != null && courseDTO.getId() > 0) {
			throw new CourseCreationException("Course should not contain ID!");
		}
		Course course = modelMapper.map(courseDTO, Course.class);
		Course courseCreated = courseService.createCourse(course);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(modelMapper.map(courseCreated, CourseDTO.class));
	}

	@GetMapping("/courses")
	public ResponseEntity<List<CourseDTO>> getAllCourses() {
		List<Course> listOfCourses = courseService.getAllCourses();
		List<CourseDTO> listOfCourseDTOs = util.mapPage(listOfCourses);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(listOfCourseDTOs);
	}

}
