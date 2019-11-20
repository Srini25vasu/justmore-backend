package com.justmehr.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.justmehr.backend.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
