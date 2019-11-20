package com.justmehr.backend.service;

import java.util.List;

import com.justmehr.backend.domain.dto.CourseDTO;

public interface CourseService {

	public CourseDTO createCourse(CourseDTO courseDTO);

	public List<CourseDTO> getAllCourses();

	public CourseDTO updateCourse(CourseDTO courseDTO);

	public void deleteCourse(CourseDTO courseDTO);

}
