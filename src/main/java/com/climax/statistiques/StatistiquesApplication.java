package com.climax.statistiques;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {"com.climax.statistiques.controllers","com.climax.statistiques.service"})
@EnableAutoConfiguration
public class StatistiquesApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatistiquesApplication.class, args);
	}

}
