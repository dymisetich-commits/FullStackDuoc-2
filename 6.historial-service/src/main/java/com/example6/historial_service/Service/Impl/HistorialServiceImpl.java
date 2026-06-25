package com.example6.historial_service.Service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.example6.historial_service.DTO.HistorialRequestDTO;
import com.example6.historial_service.DTO.HistorialResponseDTO;
import com.example6.historial_service.Feign.PedidoFeignClient;
import com.example6.historial_service.Feign.UsuarioFeignClient;
import com.example6.historial_service.Model.HistorialModel;
import com.example6.historial_service.Repository.HistorialRepository;
import com.example6.historial_service.Service.HistorialService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class HistorialServiceImpl implements HistorialService{

        private final HistorialRepository historialRepository;

        private final UsuarioFeignClient usuarioFeignClient;

        private final PedidoFeignClient pedidoFeignClient;

                // Convertir entidad a DTO
        private HistorialResponseDTO convertirDTO(HistorialModel historial) {

                HistorialResponseDTO dto =new HistorialResponseDTO();

                dto.setId(historial.getId());
                dto.setUsuarioId(historial.getUsuarioId());
                dto.setPedidoId(historial.getPedidoId());
                dto.setEvento(historial.getEvento());
                dto.setDescripcion(historial.getDescripcion());
                dto.setFecha(historial.getFecha());

                return dto;
        }

        // Obtener todos
        @Override
        public List<HistorialResponseDTO> obtenerTodos() {

                log.info("Listando historial");

                return historialRepository.findAll()
                        .stream()
                        .map(this::convertirDTO)
                        .toList();
        }


        //obtenermos por id
        @Override 
        public HistorialResponseDTO obtenerPorId(Long id){
                log.info("Buscando Historial {}", id);

                HistorialModel historialModel = historialRepository.findById(id)
                                .orElseThrow(() -> new NoSuchElementException("Historial no encontrado"));

                return convertirDTO(historialModel);
        
        }

        // Obtener por usuario
        @Override
        public List<HistorialResponseDTO> obtenerPorUsuario(Long usuarioId) {

                log.info("Buscando historial usuario {}",usuarioId);

                return historialRepository
                        .findByUsuarioId(usuarioId)
                        .stream()
                        .map(this::convertirDTO)
                        .toList();
        }

         // Obtener por pedido
        @Override
        public List<HistorialResponseDTO> obtenerPorPedido(Long pedidoId) {

                log.info("Buscando historial pedido {}",pedidoId);

                return historialRepository
                        .findByPedidoId(pedidoId)
                        .stream()
                        .map(this::convertirDTO)
                        .toList();
        }



        @Override
        public HistorialResponseDTO crearHistorial(
                HistorialRequestDTO dto) {

        log.info("Creando historial");

        // validar usuario
        var usuario =
                usuarioFeignClient.obtenerUsuario(
                        dto.getUsuarioId());

        log.info("Usuario encontrado {}",
                usuario.getNombre());

        // validar pedido
        if(dto.getPedidoId() != null){

                var pedido =
                        pedidoFeignClient.obtenerPedido(
                                dto.getPedidoId());

                log.info("Pedido encontrado {}",
                        pedido.getId());
        }

        HistorialModel historial =
                new HistorialModel();

        historial.setUsuarioId(dto.getUsuarioId());

        historial.setPedidoId(dto.getPedidoId());

        historial.setEvento(dto.getEvento());

        historial.setDescripcion(
                dto.getDescripcion());

        historial.setFecha(
                LocalDateTime.now());

        HistorialModel guardado =
                historialRepository.save(
                        historial);

        log.info("Historial creado {}",
                guardado.getId());

        return convertirDTO(guardado);
        }

        // Eliminar historial
        @Override
        public void eliminarHistorial(Long id) {

                log.info("Eliminando historial {}", id);

                HistorialModel historial =
                        historialRepository.findById(id).orElseThrow(() ->new NoSuchElementException("Historial no encontrado"));

                historialRepository.delete(historial);
        }

    
}
