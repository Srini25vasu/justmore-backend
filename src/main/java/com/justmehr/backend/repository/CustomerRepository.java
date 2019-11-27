package com.justmehr.backend.repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.scheduling.annotation.Async;

import com.justmehr.backend.domain.Course;
import com.justmehr.backend.domain.Customer;

/**
 * Repository to manage Course instances in streaming fashion
 * @author Srinivasu
 * @version 1.0.0
 * @since 2019-11-20
 *
 */
public interface CustomerRepository extends Repository<Customer, Long>, JpaRepository<Customer, Long>{

	/**
	 * Special customization of {@link CrudRepository.findOne(java.io.Serializable)} to return {@link Optional} 
	 * @param id
	 * @return
	 */
	Optional<Customer> findById(Long id);
	
	/**
	 * Save the given {@link Customer}
	 * @param <S>
	 * @param customer
	 * @return
	 */
	<S extends Customer> S save(S customer); 
	
	/**
	 * Simple method to derive a query from using jdk 8's {@link Optional} as return type
	 * @param courseType
	 * @return
	 */
	Optional<Customer> findByLastName(String name);
	
	/**
	 * default method to derive a query to find {@link Course} @
	 * @param course
	 * @return
	 */
	default Optional<Customer> findByLastName(Customer customer){
		return findByLastName( customer == null ? null: customer.getLastName());
	}
	/**
	 * method to execute a custom query with {@link Stream} as return type. The query is executed
	 * in a streaming fashion which means that the method returns as soon as the first results are ready.
	 * @return
	 */
	@Query("Select c from customer c")
	Stream<Customer> streamAllCustomers();
	
	/**
	 * Method to execute a derived query with {@link Stream} as return type.The query is executed
	 * in a streaming fashion which means that the method returns as soon as the first results are ready.
	 * @return
	 */
	@EntityGraph(value = "Customer.courses")
	Stream<Customer> findAllByLastNameIsNotNull();
	
	@EntityGraph(value = "Customer.courses")
	Page<Customer> findAll(Pageable pageRequest);
		
	@Async
	CompletableFuture<List<Customer>> readAllBy();
	
	
}
