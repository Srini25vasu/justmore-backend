package com.justmehr.backend.repository.java8;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.scheduling.annotation.Async;

import com.justmehr.backend.domain.Course;

/**
 * Repository to manage Course instances in streaming fashion
 * @author Srinivasu
 *
 */
public interface CourseStreamRepository extends Repository<Course, Long> {

	/**
	 * Special customization of {@link CrudRepository.findOne(java.io.Serializable)} to return {@link Optional} 
	 * @param id
	 * @return
	 */
	Optional<Course> findById(Long id);
	
	/**
	 * Save the given {@link Course}
	 * @param <S>
	 * @param course
	 * @return
	 */
	<S extends Course> S save(S course); 
	
	/**
	 * Simple method to derive a query from using jdk 8's {@link Optional} as return type
	 * @param courseType
	 * @return
	 */
	Optional<Course> findByCourseType(String courseType);
	
	/**
	 * default method to derive a query to find {@link Course}
	 * @param course
	 * @return
	 */
	default Optional<Course> findByCourseType(Course course){
		return findByCourseType( course == null ? null: course.getCourseType());
	}
	/**
	 * method to execute a custom query with {@link Stream} as return type. The query is executed
	 * in a streaming fashion which means that the method returns as soon as the first results are ready.
	 * @return
	 */
	@Query("Select c from course c")
	Stream<Course> streamAllCourses();
	
	/**
	 * Method to execute a derived query with {@link Stream} as return type.The query is executed
	 * in a streaming fashion which means that the method returns as soon as the first results are ready.
	 * @return
	 */
	Stream<Course> findAllByCourseTypeIsNotNull();
		
	@Async
	CompletableFuture<List<Course>> readAllBy();
	
	
}
