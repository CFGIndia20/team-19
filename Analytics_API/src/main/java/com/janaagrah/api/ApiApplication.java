package com.janaagrah.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.google.cloud.firestore.*;

/**
 * 
 * @author jimil
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.janaagrah.controllers","com.janaagrah.services",
		"com.janaagrah.repositories", "com.janaagrah.*"})
public class ApiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
