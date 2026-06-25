package example.eureka_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// Importa la anotación de Eureka Server
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

// Marca la clase principal de Spring Boot
@SpringBootApplication

// Activa Eureka Server
@EnableEurekaServer // <--- ESTA ANOTACIÓN ES LA MÁGICA
public class EurekaServerApplication {

  // Inicia la aplicación
	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	
    //http://localhost:8761
  
  
  }

}



/*
# Puerto del servidor Eureka
server:
  port: 8761

spring:
  application:
    
    # Nombre del microservicio
    name: eureka-server

eureka:
  client:
    
    # Eureka NO se registra a sí mismo
    register-with-eureka: false
    
    # Eureka NO necesita buscar otros servidores
    fetch-registry: false
*/

/*
http://localhost:8761
*/











/*
server:
  port: 8080

spring:
  application:
    name: api-gateway

  cloud:
    gateway:
      server:
        webflux:
          routes:

            # USUARIO SERVICE
            - id: usuario-service
              uri: lb://USUARIO-SERVICE
              predicates:
                - Path=/api/usuarios/**

            # RESTAURANTE SERVICE
            - id: restaurante-service
              uri: lb://RESTAURANTE-SERVICE
              predicates:
                - Path=/api/restaurantes/**

            # PEDIDO SERVICE
            - id: pedido-service
              uri: lb://PEDIDO-SERVICE
              predicates:
                - Path=/api/pedidos/**

            # PAGO SERVICE
            - id: pago-service
              uri: lb://PAGO-SERVICE
              predicates:
                - Path=/api/pagos/**

            # ENTREGA SERVICE
            - id: entrega-service
              uri: lb://ENTREGA-SERVICE
              predicates:
                - Path=/api/entregas/**

            # HISTORIAL SERVICE
            - id: historial-service
              uri: lb://HISTORIAL-SERVICE
              predicates:
                - Path=/api/historial/**

            # NOTIFICACION SERVICE
            - id: notificacion-service
              uri: lb://NOTIFICACION-SERVICE
              predicates:
                - Path=/api/notificaciones/**

            # REPARTIDOR SERVICE
            - id: repartidor-service
              uri: lb://REPARTIDOR-SERVICE
              predicates:
                - Path=/api/repartidores/**

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/

*/

