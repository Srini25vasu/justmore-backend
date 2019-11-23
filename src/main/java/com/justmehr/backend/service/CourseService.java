package com.justmehr.backend.service;

import java.util.List;

import com.justmehr.backend.domain.Course;

/**
 * Service to manage course instances
 * 
 * @author Srinivasu
 * @version 1.0.0
 * @since 2019-11-20
 *
 */
public interface CourseService {

	/**
	 * method to save the given {@link Course}
	 * @param course
	 * @return
	 */
	public Course createCourse(Course course);

	public List<Course> getAllCourses();

	public Course updateCourse(Course course);

	public void deleteCourse(Course course);

}
