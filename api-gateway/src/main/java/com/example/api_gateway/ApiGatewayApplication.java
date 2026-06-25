package com.example.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


// Marca el proyecto como Spring Boot
@SpringBootApplication

// Permite registrarse en Eureka
@EnableDiscoveryClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}



/*
MUY IMPORTANTE

lb://

significa:

Load Balancer con Eureka

Entonces el gateway busca automáticamente el servicio registrado.
*/



/*
Ahora TODOS tus microservicios deben tener esto

En cada application.yml:

spring:
  application:
    name: restaurante-service

⚠️ El nombre debe coincidir EXACTAMENTE con:

uri: lb://RESTAURANTE-SERVICE

Spring automáticamente lo convierte a mayúsculas.
*/

/*

TODOS LOS MICROSERVICIOS DEBEN TENER EUREKA
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
*/