package com.justmehr.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.justmehr.backend.domain.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}
