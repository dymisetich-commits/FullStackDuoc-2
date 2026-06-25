package com.example.restaurante_service.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurante_service.Dto.RestauranteRequestDTO;
import com.example.restaurante_service.Dto.RestauranteResponseDTO;
import com.example.restaurante_service.Service.RestauranteService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

//indica que es controlador REST
@RestController

//ruta principal o base 
@RequestMapping("/api/restaurantes")

//logs
@Slf4j

public class RestauranteController {

  //inyeccion del service
  @Autowired
  private RestauranteService restauranteService;


  //get listamos todos
  @GetMapping
  public List<RestauranteResponseDTO> listar(){
    log.info("GET listar restaurantes");

    return restauranteService.findAll();

  }

  //GET por ID
  @GetMapping("/{id}")
  public RestauranteResponseDTO obtener(@PathVariable Long id){
    log.info("GET restaurante {}", id);

    return restauranteService.findById(id);

  }


  //POST crear restaurante 
  @PostMapping
  public RestauranteResponseDTO crear(@Valid @RequestBody RestauranteRequestDTO dto){
    log.info("POST crear estaurante");
    return restauranteService.save(dto);
  }

  //put que el que actualiza
  @PutMapping("/{id}")
  public RestauranteResponseDTO actualizar( @PathVariable Long id, @Valid @RequestBody RestauranteRequestDTO dto){
        log.info("PUT actuazliar restaurante {}", id);
        return restauranteService.update(id, dto);
    }

    //DELETE 
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
      log.info("DELETE restaurante {}", id);

      restauranteService.delete(id);
    }
}


/*
-- Crear base de datos en mysql y despues esto se vera en eureka epicooo
CREATE DATABASE restaurante_db;
*/


/*
POST http://localhost:8081/api/restaurantes
*/

/*
{
  "nombre": "KFC",
  "direccion": "Santiago",
  "telefono": "12345678",
  "tipoComida": "Rapida",
  "activo": true
}
*/