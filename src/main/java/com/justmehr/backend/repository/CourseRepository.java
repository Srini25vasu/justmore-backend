package com.justmehr.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.justmehr.backend.domain.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{
	
	List<Course> findAllByName(String name);
	List<Course> findAllByIdOrderByStatus(Long id);
	List<Course> findAllByNameAndStatus(String name, String status);

}
