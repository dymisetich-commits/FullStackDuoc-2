los swaager de los microservicios

| Microservicio        | Puerto | Swagger                                       |
| -------------------- | ------ | --------------------------------------------- |
| Usuario Service      | 8089   | `http://localhost:8089/swagger-ui/index.html` |
| Restaurante Service  | 8081   | `http://localhost:8081/swagger-ui/index.html` |
| Pedido Service       | 8083   | `http://localhost:8083/swagger-ui/index.html` |
| Pago Service         | 8084   | `http://localhost:8084/swagger-ui/index.html` |
| Entrega Service      | 8085   | `http://localhost:8085/swagger-ui/index.html` |
| Historial Service    | 8086   | `http://localhost:8086/swagger-ui/index.html` |
| Notificación Service | 8087   | `http://localhost:8087/swagger-ui/index.html` |
| Repartidor Service   | 8088   | `http://localhost:8088/swagger-ui/index.html` |
| Categoría Service    | 8090   | `http://localhost:8090/swagger-ui/index.html` |
| Sucursal Service     | 8091   | `http://localhost:8091/swagger-ui/index.html` |



---------------------------------------------------
debe tener el readme estas cosas: 

Integrantes
Arquitectura
Cómo ejecutar
Orden de arranque
Bases de datos
Eureka
Swagger
Gateway
Link del video


---------------------------------------------------------


server:
  port: 8089

spring:
  application:
    name: usuario-service

  datasource:
    url: jdbc:mysql://localhost:3306/usuario_db
    username: root
    password: 

  jpa:
    hibernate:
      ddl-auto: update

    show-sql: true

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/


-------------------------------------------------------------

Requisitos
- Java 21
- Maven 3.9+

Ejecución

1. mvn clean install -DskipTests

2. Ejecutar:
   - Eureka
   - Microservicios
   - API Gateway


   