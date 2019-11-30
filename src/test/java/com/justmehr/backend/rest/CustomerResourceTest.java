package com.justmehr.backend.rest;

import com.justmehr.backend.domain.Customer;
import com.justmehr.backend.service.impl.CustomerServiceImpl;
import com.justmehr.backend.util.MapperUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class CustomerResourceTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerServiceImpl customerService;

    @MockBean
    ModelMapper modelMapper;

    @MockBean
    MapperUtil mapperUtil;

    Customer customer;

    List<Customer> customerList;

    @BeforeEach
    void setup(){

    }

    @AfterEach
    void tearDown(){
      customer = null;
      customerList = null;

    }
}