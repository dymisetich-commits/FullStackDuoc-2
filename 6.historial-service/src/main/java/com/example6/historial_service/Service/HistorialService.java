package com.example6.historial_service.Service;

import java.util.List;

import com.example6.historial_service.DTO.HistorialRequestDTO;
import com.example6.historial_service.DTO.HistorialResponseDTO;

public interface HistorialService {


    List<HistorialResponseDTO> obtenerTodos();

    // Obtener por ID
    HistorialResponseDTO obtenerPorId(Long id);

    // Obtener por usuario
    List<HistorialResponseDTO> obtenerPorUsuario(
            Long usuarioId);

    // Obtener por pedido
    List<HistorialResponseDTO> obtenerPorPedido(
            Long pedidoId);

    // Crear historial
    HistorialResponseDTO crearHistorial(HistorialRequestDTO dto);

    // Eliminar historial
    void eliminarHistorial(Long id);
}