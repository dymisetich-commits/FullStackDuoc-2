package com.example.restaurante_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestauranteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestauranteServiceApplication.class, args);
	}

}

/*
http://localhost:8081/api/restaurantes
*/

/* para el post
{
  "nombre": "KFC",
  "direccion": "Santiago",
  "telefono": "12345678",
  "tipoComida": "Rapida",
  "activo": true
}

*/


