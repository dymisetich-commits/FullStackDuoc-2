package com.example.repartidor_service.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.repartidor_service.Model.RepartidorModel;

public interface RepartidorRepository extends JpaRepository<RepartidorModel, Long> {

    // Buscar por estado
    List<RepartidorModel> findByEstado(String estado);
}