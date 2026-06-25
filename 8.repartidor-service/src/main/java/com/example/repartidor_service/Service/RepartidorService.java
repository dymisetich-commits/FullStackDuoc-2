package com.example.repartidor_service.Service;

import java.util.List;

import com.example.repartidor_service.DTO.RepartidorRequestDTO;
import com.example.repartidor_service.DTO.RepartidorResponseDTO;

public interface RepartidorService {

    // Obtener todos
    List<RepartidorResponseDTO> obtenerTodos();

    // Obtener por ID
    RepartidorResponseDTO obtenerPorId(Long id);

    // Obtener disponibles
    List<RepartidorResponseDTO> obtenerDisponibles();

    // Crear repartidor
    RepartidorResponseDTO crearRepartidor(RepartidorRequestDTO dto);

    // Actualizar
    RepartidorResponseDTO actualizarRepartidor(Long id,RepartidorRequestDTO dto);

    // Eliminar
    void eliminarRepartidor(Long id);
}