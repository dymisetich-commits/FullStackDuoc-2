package com.example3.pedido_service;

import org.springframework.boot.SpringApplication;

// import para habilitar Feign
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients // habilita comunicación entre microservicios con Feign
@SpringBootApplication
public class PedidoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedidoApplication.class, args);
	}

}




/*





/*
CREATE DATABASE pedido_db;
*/


/* 
GET http://localhost:8083/api/pedidos
*/

/*
GET http://localhost:8083/api/pedidos/1
*/

/* post
{
  "usuarioId": 1,
  "restauranteId": 1,
  "total": 25000
}
*/
/* deberia salirme en post 
{
  "id": 1,
  "usuarioId": 1,
  "restauranteId": 1,
  "fecha": "2026-05-10T03:20:00",
  "estado": "PENDIENTE",
  "total": 25000
}
*/

/* put
{
  "usuarioId": 1,
  "restauranteId": 1,
  "estado": "PAGADO",
  "total": 25000
}
*/

/*
DELETE http://localhost:8083/api/pedidos/1
*/