package com.example.sucursal_service.Service.Impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.example.sucursal_service.DTO.SucursalRequestDTO;
import com.example.sucursal_service.DTO.SucursalResponseDTO;
import com.example.sucursal_service.Model.SucursalModel;
import com.example.sucursal_service.Repository.SucursalRepository;
import com.example.sucursal_service.Service.SucursalService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SucursalServiceImpl implements SucursalService{

     private final SucursalRepository sucursalRepository;

    // Convertir entidad a DTO
    private SucursalResponseDTO convertirDTO(
            SucursalModel sucursal) {

        SucursalResponseDTO dto =
                new SucursalResponseDTO();

        dto.setId(sucursal.getId());
        dto.setNombre(sucursal.getNombre());
        dto.setDireccion(sucursal.getDireccion());

        return dto;
    }

    // Obtener todas
    @Override
    public List<SucursalResponseDTO> obtenerTodas() {

        log.info("Listando sucursales");

        return sucursalRepository.findAll()
                .stream()
                .map(this::convertirDTO)
                .toList();
    }

    // Obtener por ID
    @Override
    public SucursalResponseDTO obtenerPorId(Long id) {

        log.info("Buscando sucursal {}", id);

        SucursalModel sucursal =
                sucursalRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Sucursal no encontrada"));

        return convertirDTO(sucursal);
    }

    // Crear sucursal
    @Override
    public SucursalResponseDTO crearSucursal(
            SucursalRequestDTO dto) {

        log.info("Creando sucursal");

        SucursalModel sucursal =
                new SucursalModel();

        sucursal.setNombre(dto.getNombre());
        sucursal.setDireccion(dto.getDireccion());

        SucursalModel guardada =
                sucursalRepository.save(sucursal);

        log.info("Sucursal creada {}",
                guardada.getId());

        return convertirDTO(guardada);
    }

    // Actualizar sucursal
    @Override
    public SucursalResponseDTO actualizarSucursal(
            Long id,
            SucursalRequestDTO dto) {

        log.info("Actualizando sucursal {}", id);

        SucursalModel sucursal =
                sucursalRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Sucursal no encontrada"));

        sucursal.setNombre(dto.getNombre());
        sucursal.setDireccion(dto.getDireccion());

        SucursalModel actualizada =
                sucursalRepository.save(sucursal);

        return convertirDTO(actualizada);
    }

    // Eliminar sucursal
    @Override
    public void eliminarSucursal(Long id) {

        log.info("Eliminando sucursal {}", id);

        SucursalModel sucursal =
                sucursalRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Sucursal no encontrada"));

        sucursalRepository.delete(sucursal);
    }
}
