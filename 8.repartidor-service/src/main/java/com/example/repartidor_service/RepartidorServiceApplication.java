package com.example.repartidor_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class RepartidorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RepartidorServiceApplication.class, args);
	}

}




/*
CREATE DATABASE repartidor_db;
*/


/*
http://localhost:8088/api/repartidores
*/
/*
{ post
  {
  "id": 1,
  "nombre": "Juan Perez",
  "telefono": "987654321",
  "estado": "DISPONIBLE",
  "vehiculo": "Moto"
}
}
*/


/* put
{
  "nombre": "Juan Perez",
  "telefono": "999999999",
  "estado": "OCUPADO",
  "vehiculo": "Moto Yamaha"
}
*/



//
/*en caso que falle pox

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">

	<modelVersion>4.0.0</modelVersion>

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>4.0.6</version>
		<relativePath/>
	</parent>

	<groupId>com.example</groupId>
	<artifactId>repartidor-service</artifactId>
	<version>0.0.1-SNAPSHOT</version>

	<properties>
		<java.version>21</java.version>
		<spring-cloud.version>2025.1.1</spring-cloud.version>
	</properties>

	<dependencies>

		<!-- WEB -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<!-- JPA -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<!-- VALIDACIONES -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>

		<!-- EUREKA CLIENT -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>

		<!-- OPENFEIGN -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-openfeign</artifactId>
		</dependency>

		<!-- MYSQL -->
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>

		<!-- LOMBOK -->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>

		<!-- TEST -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

	</dependencies>

	<dependencyManagement>
		<dependencies>

			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>

		</dependencies>
	</dependencyManagement>

	<build>
		<plugins>

			<!-- SPRING BOOT -->
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>

			<!-- LOMBOK -->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>

				<configuration>
					<annotationProcessorPaths>

						<path>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</path>

					</annotationProcessorPaths>
				</configuration>
			</plugin>

		</plugins>
	</build>

</project>

*/