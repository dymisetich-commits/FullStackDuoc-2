package com.example7.notificacion_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class NotifiacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotifiacionApplication.class, args);
	}

}



/*
CREATE DATABASE notificacion_db;
*/

/*
POST http://localhost:8087/api/notificaciones
*/

/*
{
  "usuarioId": 1,
  "pedidoId": 5,
  "tipo": "EMAIL",
  "mensaje": "Tu pedido fue enviado"
}
*/
