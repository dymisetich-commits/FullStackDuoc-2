package com.example6.historial_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class HistorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(HistorialApplication.class, args);
	}

}



/*
CREATE DATABASE historial_db;
*/

/*
POST http://localhost:8086/api/historial
*/

/*
{
  "usuarioId": 1,
  "pedidoId": 2,
  "evento": "PEDIDO_CREADO",
  "descripcion": "El usuario creó un nuevo pedido"
}
*/



