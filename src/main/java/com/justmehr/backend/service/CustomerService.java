package com.justmehr.backend.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.justmehr.backend.domain.Customer;
/**
 * Service to manage {@link Customer} instances
 * @author Srinivasu
 * @version 1.0.0
 * @since 2019-11-23
 */
public interface CustomerService {
	
	/**
	 * method to find customers by last name
	 * @param lastName
	 * @return List of customers
	 */
	public List<Customer> findCustomerByLastName(String lastName);
	/**
	 * method to get all customers
	 * @return list of customers
	 */
	public List<Customer> findAllCustomers();
	
	/**
	 * method to create a customer with a given {@link Customer} instance
	 * @param customer
	 * @return created customer
	 */
	public Customer createCustomer(Customer customer);
	
	public Page<Customer> findAll(Pageable pageRequest);

}
