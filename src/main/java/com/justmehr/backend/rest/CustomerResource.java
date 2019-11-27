package com.justmehr.backend.rest;

import java.util.List;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.justmehr.backend.domain.Customer;
import com.justmehr.backend.domain.dto.CourseDTO;
import com.justmehr.backend.domain.dto.CustomerDTO;
import com.justmehr.backend.service.CustomerService;
import com.justmehr.backend.util.MapperUtil;

/**
 * REST API for management of Customer resources
 * 
 * @author Srinivasu
 * @version 1.0.0
 * @since 2019-11-23
 *
 */
@RestController
@RequestMapping("/api")
public class CustomerResource {

	private final Logger logger = Logger.getLogger(com.justmehr.backend.rest.CustomerResource.class);

	@Autowired
	CustomerService customerService;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	MapperUtil util;

	@PostMapping("/customers")
	public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO, Pageable pageRequest) {

		Customer customer = customerService.createCustomer(modelMapper.map(customerDTO, Customer.class));
		CustomerDTO createdCustomer = modelMapper.map(customer, CustomerDTO.class);
		return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(createdCustomer);
	}

	@GetMapping("/customers")
	public ResponseEntity<List<CustomerDTO>> getAllCustomers(Pageable pageRequest) {

		Page<Customer> listOfCustomers = customerService.findAll(pageRequest);
		List<CustomerDTO> listOfCustomerDTOs = util.mapAll(listOfCustomers.getContent(), CustomerDTO.class);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(listOfCustomerDTOs);

	}

}
