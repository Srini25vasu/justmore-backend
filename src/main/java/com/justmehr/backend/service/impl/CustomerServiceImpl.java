package com.justmehr.backend.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.justmehr.backend.domain.Customer;
import com.justmehr.backend.repository.CustomerRepository;
import com.justmehr.backend.service.CustomerService;

/**
 * Implementation of {@link CustomerService}
 * 
 * @author Srinivasu
 * @version 1.0.0
 * @since 2019-11-20
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	private final Logger logger = LoggerFactory.getLogger(com.justmehr.backend.service.impl.CustomerServiceImpl.class);
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public List<Customer> findCustomerByLastName(String lastName) {

		Stream<Customer> stream = customerRepository.findAllByLastNameIsNotNull();
		return stream.filter(customer -> customer.getLastName() == lastName).collect(Collectors.toList());

	}

	@Override
	@Transactional(readOnly = true)
	public List<Customer> findAllCustomers() {
		//return customerRepository.findAllByLastNameIsNotNull().collect(Collectors.toList());
		return customerRepository.findAll();
	}
	
	public Page<Customer> findAll(Pageable pageRequest){
		
		return customerRepository.findAll(pageRequest);
	}

	@Override
	public Customer createCustomer(Customer customer) {
		if (customer.getId() != null && customer.getId() >= 0) {
			logger.debug("Customer id should not exist before creation of a customer");
			throw new RuntimeException("Customer id should not exist!");
		}
		
		customer.getCoursesOptional().ifPresent(courses->courses.forEach(course->course.get().setCustomer(customer)));
		
		Customer createdCustomer = customerRepository.save(customer);
		logger.debug("Created {} successfully", createdCustomer);
		return createdCustomer;
	}

}
