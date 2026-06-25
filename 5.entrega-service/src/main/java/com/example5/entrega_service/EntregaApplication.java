package com.example5.entrega_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients //lo de fieng
@SpringBootApplication
public class EntregaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntregaApplication.class, args);
	}

}




/*
CREATE DATABASE entrega_db; falta probar 
*/

/* probamos por post 

http://localhost:8084/api/entregas

{
  "pedidoId": 1,
  "repartidor": "Juan Perez",
  "direccion": "Santiago Centro 123"
}
*/

