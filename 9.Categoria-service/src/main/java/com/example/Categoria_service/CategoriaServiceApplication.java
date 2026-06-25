package com.example.Categoria_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CategoriaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategoriaServiceApplication.class, args);
	}

}
