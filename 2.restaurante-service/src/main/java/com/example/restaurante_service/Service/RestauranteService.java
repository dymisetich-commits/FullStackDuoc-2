package com.example.restaurante_service.Service;

import java.util.List;

import com.example.restaurante_service.Dto.RestauranteRequestDTO;
import com.example.restaurante_service.Dto.RestauranteResponseDTO;

//interfaz define las operaciones del servicio , separo en inteerfaz y em implementacion segun tengo entendido
public interface RestauranteService {


    //Listamos todos
    List<RestauranteResponseDTO> findAll();

    //buscamos por id
    RestauranteResponseDTO findById(Long id);

    //guardar restaurante 
    RestauranteResponseDTO save(RestauranteRequestDTO dto);

    //actualizar restaurante 
    RestauranteResponseDTO update(Long id, RestauranteRequestDTO dto);

    //eliminar restaurante
    void delete(Long id);


}

