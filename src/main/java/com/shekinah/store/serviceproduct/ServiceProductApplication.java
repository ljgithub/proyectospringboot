package com.shekinah.store.serviceproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EntityScan("com/shekinah/store/serviceproduct/entity")
@EnableAutoConfiguration
@SpringBootApplication
@EnableConfigurationProperties

public class ServiceProductApplication {

	public static void main(String[] args) {

		SpringApplication.run(ServiceProductApplication.class, args);
	}

}
