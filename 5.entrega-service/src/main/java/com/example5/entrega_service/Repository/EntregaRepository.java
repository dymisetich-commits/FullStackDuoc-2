package com.example5.entrega_service.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example5.entrega_service.Model.EntregaModel;

public interface EntregaRepository extends JpaRepository <EntregaModel, Long>{


    // Buscar por pedido
    List<EntregaModel> findByPedidoId(Long pedidoId);

    // Buscar por estado
    List<EntregaModel> findByEstado(String estado);

    // Buscar por repartidor
    List<EntregaModel> findByRepartidor(String repartidor);
}