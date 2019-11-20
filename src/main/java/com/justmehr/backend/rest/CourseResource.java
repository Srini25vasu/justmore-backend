package com.justmehr.backend.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.justmehr.backend.domain.dto.CourseDTO;
import com.justmehr.backend.exception.CourseCreationException;
import com.justmehr.backend.service.CourseService;

@RestController
@RequestMapping("/api")
public class CourseResource {
	@Autowired
	CourseService courseService;

    @PostMapping("/courses")
	public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) {

		if (courseDTO.getId() != null && courseDTO.getId() > 0) {
			throw new CourseCreationException("Course should not contain ID!");
		}
		CourseDTO courseCreated = courseService.createCourse(courseDTO);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(courseCreated);
	}
    
    @GetMapping("/courses")
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
    	List<CourseDTO> listOfCourses = courseService.getAllCourses();
    	return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(listOfCourses);
    }

}
