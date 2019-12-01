package com.justmehr.backend.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.justmehr.backend.domain.Course;
import com.justmehr.backend.domain.dto.CourseDTO;
import com.justmehr.backend.service.CourseService;
import com.justmehr.backend.service.impl.CourseServiceImpl;
import com.justmehr.backend.util.MapperUtil;

@WebMvcTest(controllers = CourseResource.class)
class CourseResourceTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	CourseServiceImpl courseService;

	@MockBean
	ModelMapper modelMapper;

	@MockBean
	MapperUtil mapperUtil;

	Course course;
	List<Course> list;

	@Autowired
	ObjectMapper objectMapper;

	@BeforeEach
	void setUp() throws Exception {
		course = new Course();
		course.setId(1L);
		course.setName("Software-java");

		List<Course> listOfCourses = new ArrayList();
		listOfCourses.add(course);

		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setId(1L);
		courseDTO.setName("Software-java");

		List<CourseDTO> listOfCourseDTOs = new ArrayList();
		listOfCourseDTOs.add(courseDTO);

		when(courseService.getAllCourses()).thenReturn(listOfCourses);
		when(mapperUtil.mapPage(listOfCourses)).thenReturn(listOfCourseDTOs);
	}

	@AfterEach
	void tearDown() throws Exception {
		course = null;
		list = null;
	}
	

	@Test
	@DisplayName("Test Get All Courses")
	void testGetAllCourses() {

		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/api/courses").contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
					.andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
					.andExpect((ResultMatcher) content().json(objectMapper.writeValueAsString(list)))
					.andExpect(jsonPath("$.courseDTO.name").value("Software-java"));

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Test
	@DisplayName("Test with valid input then returns 200")
	void whenTheValidInput_thenReturns200() throws Exception {
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setName("Software training - java");

		course = new Course();
		course.setName("Software training - java");

		when(modelMapper.map(courseDTO, Course.class)).thenReturn(course);
		when(modelMapper.map(course, CourseDTO.class)).thenReturn(courseDTO);

		mockMvc.perform(post("/api/courses").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(course))).andExpect(status().isOk());

	}

	@Test
	@DisplayName("Test with null input then returns 400")
	void whenNullInput_thenReturns400() throws Exception {
		course = new Course();
		course.setName(null);
		
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setName(null);

		when(modelMapper.map(courseDTO, Course.class)).thenReturn(course);
		when(modelMapper.map(course, CourseDTO.class)).thenReturn(courseDTO);

		mockMvc.perform(post("/api/courses").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(course))).andExpect(status().isBadRequest());

	}
	@Test
	@DisplayName("Test for CreateCourseException when id exist in the input")
	void testCreateCourseException() throws JsonProcessingException, Exception {
		
		course = new Course();
		course.setId(1L);
		course.setName("Software");
		
		mockMvc.perform(post("/api/courses").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				        .content(objectMapper.writeValueAsString(course))).andExpect(status().is5xxServerError());
		                
	}

}
