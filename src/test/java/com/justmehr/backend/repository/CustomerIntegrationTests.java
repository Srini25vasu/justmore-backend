package com.justmehr.backend.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.justmehr.backend.domain.Customer;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class CustomerIntegrationTests {

	@Autowired
	CustomerRepository customerRepository;

	@Test
	@DisplayName("Test if it provides one optional with findById")
	void providesFindOneWithOptional() {

		Customer customer = new Customer();
		customer.setFirstName("Srini");
		customer.setLastName("Kakaraparti");
		Customer savedCustomer = customerRepository.save(customer);

		assertThat(customerRepository.findById(savedCustomer.getId())).isPresent();
		assertThat(customerRepository.findById(savedCustomer.getId() + 1)).isEmpty();

	}

	@Test
	@DisplayName("Test if default method is invoked")
	void invokesDefaultMethd() {
		Customer customer = new Customer();
		customer.setFirstName("Srini");
		customer.setLastName("Kakaraparti");
		Customer savedCustomer = customerRepository.save(customer);

		Optional<Customer> optional = customerRepository.findByLastName(customer);
		assertThat(optional).hasValue(customer);
	}

	@Test
	@DisplayName("Test if the custom query is executed returning a stream")
	void useStreamWithCustomQuery() {

		Customer customer1 = new Customer();
		customer1.setFirstName("Srini1");
		customer1.setLastName("Kakaraparti");
		customerRepository.save(customer1);

		Customer customer2 = new Customer();
		customer2.setFirstName("Srini2");
		customer2.setLastName("Kakaraparti");
		customerRepository.save(customer2);

		try (Stream<Customer> stream = customerRepository.findAllByLastNameIsNotNull()) {

			assertThat(stream.collect(Collectors.toList())).contains(customer1, customer2);
		}

	}	

}
