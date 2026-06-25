package com.example5.entrega_service.Service;

import java.util.List;

import com.example5.entrega_service.DTO.EntregaRequestDTO;
import com.example5.entrega_service.DTO.EntregaResponseDTO;

public interface EntregaService {

    //obtener todas
    List<EntregaResponseDTO> obtenerTodas();

    //obtener por id
    EntregaResponseDTO obtenerPorId(Long id);

    //crear entrega 
    EntregaResponseDTO crearEntrega(EntregaRequestDTO dto);
    //eliminar entrega 
    void eliminarEntrega(Long id);
}