package com.justmehr.backend.util;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.justmehr.backend.domain.Course;
import com.justmehr.backend.domain.dto.CourseDTO;

@Component
public class MapperUtil {
	
	@Autowired
	ModelMapper modelMapper;
	
	public List<CourseDTO> mapPage(List<Course> courses) {
		List<CourseDTO> listDTOs;
		listDTOs = courses.stream().map(house -> modelMapper.map(house, CourseDTO.class))
				.collect(Collectors.<CourseDTO>toList());
		return listDTOs;
	}


}
