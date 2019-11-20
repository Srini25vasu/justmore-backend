package com.justmehr.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justmehr.backend.domain.Course;
import com.justmehr.backend.domain.dto.CourseDTO;
import com.justmehr.backend.exception.CourseCreationException;
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
	public CourseDTO createCourse(CourseDTO courseDTO) {

		Course course = modelMapper.map(courseDTO, Course.class);
		Course courseCreated = courseRepository.save(course);
		return modelMapper.map(courseCreated, CourseDTO.class);
	}

	@Override
	public List<CourseDTO> getAllCourses() {
		List<Course> listOfCourses = courseRepository.findAll();
		return mapPage(listOfCourses);
	}

	@Override
	public CourseDTO updateCourse(CourseDTO courseDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCourse(CourseDTO courseDTO) {
		// TODO Auto-generated method stub

	}

	public List<CourseDTO> mapPage(List<Course> courses) {
		List<CourseDTO> listDTOs = new ArrayList();
		listDTOs = courses.stream().map(house -> modelMapper.map(house, CourseDTO.class))
				.collect(Collectors.<CourseDTO>toList());
		return listDTOs;
	}

}
