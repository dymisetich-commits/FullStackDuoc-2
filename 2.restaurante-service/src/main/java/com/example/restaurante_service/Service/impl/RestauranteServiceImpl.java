package com.example.restaurante_service.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurante_service.Dto.RestauranteRequestDTO;
import com.example.restaurante_service.Dto.RestauranteResponseDTO;
import com.example.restaurante_service.Model.RestauranteModelo;
import com.example.restaurante_service.Repository.RestauranteRepository;
import com.example.restaurante_service.Service.RestauranteService;

import lombok.extern.slf4j.Slf4j;

//Marca esta clase como servicio
@Service

//Habilita los logs y muestra en consola las cosas
@Slf4j

public class RestauranteServiceImpl implements RestauranteService {


    //inyeccion del repository lo que siempre hacemos con el crud se mantiene 
    @Autowired
    private RestauranteRepository restauranteRepository;

    //Metodo para convertir entity en DTO
     private RestauranteResponseDTO map(RestauranteModelo r){

        return new RestauranteResponseDTO(
                r.getId(),
                r.getNombre(),
                r.getDireccion(),
                r.getTelefono(),
                r.getTipoComida(),
                r.getActivo()
        );
    }
     
    //Listar restaurantes y el override sirve sobrescribir
    @Override
    public List<RestauranteResponseDTO> findAll(){

        log.info("Listando restaurantes");

        return restauranteRepository.findAll()
                    .stream()
                    .map(this::map)
                    .toList();
    }

    //Buscar por ID
    @Override
    public RestauranteResponseDTO findById(Long id){
        log.info("QBuscando restaurante  {} ", id);

        RestauranteModelo r = restauranteRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));
    
        return map(r);
    }

    //guardar restaurante
    @Override
    public RestauranteResponseDTO save(RestauranteRequestDTO dto){
        log.info("Guardando restaurante calma {}", dto.getNombre());

        RestauranteModelo r = new RestauranteModelo(
            null,
            dto.getNombre(),
            dto.getDireccion(),
            dto.getTelefono(),
            dto.getTipoComida(),
            dto.getActivo()
        );
        return map(restauranteRepository.save(r));
    }

    //actualizar restaurante 
    @Override
    public RestauranteResponseDTO update(Long id, RestauranteRequestDTO dto){
        log.info("Acualizando restaurante jejeje {}", id);

        RestauranteModelo r = restauranteRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));


        r.setNombre(dto.getNombre());
        r.setDireccion(dto.getDireccion());
        r.setTelefono(dto.getTelefono());
        r.setTipoComida(dto.getTipoComida());
        r.setActivo(dto.getActivo());

        return map(restauranteRepository.save(r));
    }


    //eliminar restaurante
    @Override
    public void delete(Long id){
        log.info("Eliminando restaurante {}", id);

        restauranteRepository.deleteById(id);
    }


}