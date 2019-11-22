package com.justmehr.backend.service;

import java.util.List;

import com.justmehr.backend.domain.Course;
import com.justmehr.backend.domain.dto.CourseDTO;

public interface CourseService {

	public Course createCourse(Course course);

	public List<Course> getAllCourses();

	public Course updateCourse(Course course);

	public void deleteCourse(Course course);

}
