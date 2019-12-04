package com.justmehr.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class JustmehrBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(JustmehrBackendApplication.class, args);
	}

}
