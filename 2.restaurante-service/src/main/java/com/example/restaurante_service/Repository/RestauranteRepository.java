package com.example.restaurante_service.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restaurante_service.Model.RestauranteModelo;

//JpaRepository maneja CRUD automaticamente
public interface RestauranteRepository extends JpaRepository<RestauranteModelo, Long> {

    //Buscar restaurantes por estado activo
    List<RestauranteModelo> findByActivo(Boolean activo);

    //Buscar restaurantes por tipo de comida
    List<RestauranteModelo> findByTipoComida(String tipoComida);


}
