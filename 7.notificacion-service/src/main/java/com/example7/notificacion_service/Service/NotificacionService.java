package com.example7.notificacion_service.Service;

import java.util.List;

import com.example7.notificacion_service.DTO.NotificacionRequestDTO;
import com.example7.notificacion_service.DTO.NotificacionResponseDTO;

public interface NotificacionService {

        //obtener todas 
        List<NotificacionResponseDTO> obtenerTodas();

        //obtener por ID
        NotificacionResponseDTO obtenerPorId(Long id);

        //obtener por usuario
        List<NotificacionResponseDTO> obtenerPorUsuario(Long usuarioId);

        //obtener por pedidos
        List<NotificacionResponseDTO> obtenerPorPedido(Long pedidoId);
       
        //crear notificacion
        NotificacionResponseDTO crearNotificacion(NotificacionRequestDTO dto);

        //eliminar
        void eliminarNotificacion(Long id);
}       
