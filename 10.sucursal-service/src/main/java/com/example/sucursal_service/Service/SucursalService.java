package com.example.sucursal_service.Service;

import java.util.List;

import com.example.sucursal_service.DTO.SucursalRequestDTO;
import com.example.sucursal_service.DTO.SucursalResponseDTO;

public interface SucursalService {

    List<SucursalResponseDTO> obtenerTodas();

    SucursalResponseDTO obtenerPorId(Long id);

    SucursalResponseDTO crearSucursal(
            SucursalRequestDTO dto);

    SucursalResponseDTO actualizarSucursal(
            Long id,
            SucursalRequestDTO dto);

    void eliminarSucursal(Long id);
}
