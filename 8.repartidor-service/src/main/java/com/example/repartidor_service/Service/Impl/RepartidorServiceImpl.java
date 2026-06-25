package com.example.repartidor_service.Service.Impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.example.repartidor_service.DTO.RepartidorRequestDTO;
import com.example.repartidor_service.DTO.RepartidorResponseDTO;
import com.example.repartidor_service.Model.RepartidorModel;
import com.example.repartidor_service.Repository.RepartidorRepository;
import com.example.repartidor_service.Service.RepartidorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RepartidorServiceImpl implements RepartidorService{

        // Convertir entidad a DTO
        private RepartidorResponseDTO convertirDTO(
            RepartidorModel repartidor) {

                RepartidorResponseDTO dto =
                        new RepartidorResponseDTO();

                dto.setId(repartidor.getId());

                dto.setNombre(repartidor.getNombre());

                dto.setTelefono(
                        repartidor.getTelefono());

                dto.setEstado(
                        repartidor.getEstado());

                dto.setVehiculo(
                        repartidor.getVehiculo());

                return dto;
        }


    private final RepartidorRepository repartidorRepository;

    // Obtener todos
    @Override
    public List<RepartidorResponseDTO> obtenerTodos() {

        log.info("Listando repartidores");

        return repartidorRepository.findAll()
                .stream()
                .map(this::convertirDTO)
                .toList();
    }

    // Obtener por ID
    @Override
    public RepartidorResponseDTO obtenerPorId(Long id) {

        log.info("Buscando repartidor {}", id);

        RepartidorModel repartidor = repartidorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Repartidor no encontrado"));

        return convertirDTO(repartidor);
    }

    // Obtener disponibles
    @Override
    public List<RepartidorResponseDTO> obtenerDisponibles() {

        log.info("Buscando repartidores disponibles");

        return repartidorRepository
                .findByEstado("DISPONIBLE")
                .stream()
                .map(this::convertirDTO)
                .toList();
    }

    // Crear repartidor
    @Override
    public RepartidorResponseDTO crearRepartidor(RepartidorRequestDTO dto) {

        log.info("Creando repartidor");

        RepartidorModel repartidor = new RepartidorModel();

        repartidor.setNombre(dto.getNombre());

        repartidor.setTelefono(dto.getTelefono());

        repartidor.setVehiculo(dto.getVehiculo());

        // Estado inicial
        repartidor.setEstado("DISPONIBLE");

        RepartidorModel guardado = repartidorRepository.save(repartidor);

        log.info("Repartidor creado {}",guardado.getId());

        return convertirDTO(guardado);
    }

    // Actualizar repartidor
    @Override
    public RepartidorResponseDTO actualizarRepartidor(Long id,RepartidorRequestDTO dto) {

        log.info("Actualizando repartidor {}", id);

        RepartidorModel repartidor = repartidorRepository.findById(id)
                .orElseThrow(() ->new NoSuchElementException("Repartidor no encontrado"));

        repartidor.setNombre(dto.getNombre());

        repartidor.setTelefono(dto.getTelefono());

        repartidor.setVehiculo(dto.getVehiculo());

        repartidor.setEstado(dto.getEstado());

        RepartidorModel actualizado = repartidorRepository.save(repartidor);

        return convertirDTO(actualizado);
    }

    // Eliminar
    @Override
    public void eliminarRepartidor(Long id) {

        log.info("Eliminando repartidor {}", id);

        RepartidorModel repartidor = repartidorRepository.findById(id)
                .orElseThrow(() ->new NoSuchElementException("Repartidor no encontrado"));

        repartidorRepository.delete(repartidor);
    }

    
}


