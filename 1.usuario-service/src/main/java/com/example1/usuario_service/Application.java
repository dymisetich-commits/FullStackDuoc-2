package com.example1.usuario_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}


/*
CREATE DATABASE usuario_db;
*/

/*
POST http://localhost:8089/api/usuarios/login
/*
{
  "correo": "pato@gmail.com",
  "password": "123456"
}
*/




/* http://localhost:8089/api/usuarios
{
  "nombre": "Patricio",
  "correo": "pato@gmail.com",
  "password": "123456",
  "rol": "CLIENTE"
}

*/

