package com.example7.notificacion_service.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example7.notificacion_service.Model.NotificacionModel;

public interface NotificacionRepository extends JpaRepository<NotificacionModel, Long> {

    // Buscar por usuario
    List<NotificacionModel> findByUsuarioId(
            Long usuarioId);

    // Buscar por pedido
    List<NotificacionModel> findByPedidoId(
            Long pedidoId);

    // Buscar por estado
    List<NotificacionModel> findByEstado(
            String estado);
}