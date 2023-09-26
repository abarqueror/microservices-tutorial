package com.tutorial.bikeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableEurekaClient esta deprecated.Con añadir spring-cloud-starter-netflix-eureka-client en pom es suficiente
public class BikeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BikeServiceApplication.class, args);
	}

}
