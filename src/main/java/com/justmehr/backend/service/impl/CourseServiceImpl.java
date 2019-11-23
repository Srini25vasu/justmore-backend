package com.justmehr.backend.service.impl;


import java.util.List;
import java.util.Optional;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justmehr.backend.domain.Course;

import com.justmehr.backend.exception.CourseNotFoundException;
import com.justmehr.backend.repository.CourseRepository;

import com.justmehr.backend.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	final CourseRepository courseRepository;

	@Autowired
	ModelMapper modelMapper;

	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;

	}

	@Override
	public Course createCourse(Course course) {

		return courseRepository.save(course);

	}

	@Override
	public List<Course> getAllCourses() {

		return courseRepository.findAll();

	}

	@Override
	public Course updateCourse(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCourse(Course course) {
		// TODO Auto-generated method stub

	}

	public Course getCourseById(Long id) {

		Optional<Course> optional = courseRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		throw new CourseNotFoundException("Course with a id: " + id + " not found!");

	}

	public List<Course> getCoursesByNameAndStatus(String name, String status) {
		return courseRepository.findAllByNameAndStatus(name, status);
	}

}
