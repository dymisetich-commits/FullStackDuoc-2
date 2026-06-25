package com.example6.historial_service.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example6.historial_service.Model.HistorialModel;

public interface HistorialRepository extends JpaRepository<HistorialModel, Long>{

    // Buscar historial por usuario
    List<HistorialModel> findByUsuarioId(Long usuarioId);

    // Buscar historial por pedido
    List<HistorialModel> findByPedidoId(Long pedidoId);

    // Buscar historial por evento
    List<HistorialModel> findByEvento(String evento);
}
