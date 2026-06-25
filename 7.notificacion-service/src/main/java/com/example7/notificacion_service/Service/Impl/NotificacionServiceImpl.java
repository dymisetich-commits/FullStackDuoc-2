package com.example7.notificacion_service.Service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example7.notificacion_service.DTO.NotificacionRequestDTO;
import com.example7.notificacion_service.DTO.NotificacionResponseDTO;
import com.example7.notificacion_service.Exception.ResourceNotFoundException;
import com.example7.notificacion_service.Feign.PedidoFeignClient;
import com.example7.notificacion_service.Feign.UsuarioFeignClient;
import com.example7.notificacion_service.Model.NotificacionModel;
import com.example7.notificacion_service.Repository.NotificacionRepository;
import com.example7.notificacion_service.Service.NotificacionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j

public class NotificacionServiceImpl implements NotificacionService {

        private final UsuarioFeignClient usuarioFeignClient;

        private final PedidoFeignClient pedidoFeignClient;
        //Convertir entidad a DTO
        //esto no funciona sin tener ante los DTO hechos
        private NotificacionResponseDTO convertirDTO(NotificacionModel notificacionModel){
                
                NotificacionResponseDTO dto = new NotificacionResponseDTO();
                
                dto.setId(notificacionModel.getId());
                dto.setUsuarioId(notificacionModel.getUsuarioId());
                dto.setPedidoId(notificacionModel.getPedidoId());
                dto.setTipo(notificacionModel.getTipo());
                dto.setMensaje(notificacionModel.getMensaje());
                dto.setEstado(notificacionModel.getEstado());
                dto.setFecha(notificacionModel.getFecha());

                return dto;
        }

        //lo de siempre
        private final NotificacionRepository notificacionRepository;

        //obtener todos
        @Override
        public List<NotificacionResponseDTO> obtenerTodas(){
                
                log.info("Listando notificaciones");

                return notificacionRepository.findAll()
                        .stream().map(this::convertirDTO).toList();
        
        }

        //obtener por id 
        @Override
        public NotificacionResponseDTO obtenerPorId(Long id){

                log.info("Buscando notificacion {}", id);

                NotificacionModel notificacion = notificacionRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Notificacion no encontrada"));

                
                        return convertirDTO(notificacion);

        }

        //obetner por usuario
        @Override
        public List<NotificacionResponseDTO> obtenerPorUsuario(Long usuarioId) {

                log.info("Buscando notificaciones usuario {}", usuarioId);

                return notificacionRepository
                        .findByUsuarioId(usuarioId)
                        .stream()
                        .map(this::convertirDTO)
                        .toList();
        }

        //obtener ppor pedido
        @Override
        public List<NotificacionResponseDTO> obtenerPorPedido(Long pedidoId) {

                log.info("Buscando notificaciones pedido {}", pedidoId);

                return notificacionRepository
                        .findByPedidoId(pedidoId)
                        .stream()
                        .map(this::convertirDTO)
                        .toList();
        }

        //crear notificacion
        @Override
        public NotificacionResponseDTO crearNotificacion(
                NotificacionRequestDTO dto){

        log.info("Creando notificacion");

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

        NotificacionModel notificacionModel =
                new NotificacionModel();

        notificacionModel.setUsuarioId(
                dto.getUsuarioId());

        notificacionModel.setPedidoId(
                dto.getPedidoId());

        notificacionModel.setTipo(
                dto.getTipo());

        notificacionModel.setMensaje(
                dto.getMensaje());

        // estado inicial
        notificacionModel.setEstado(
                "ENVIADA");

        // fecha automática
        notificacionModel.setFecha(
                LocalDateTime.now());

        NotificacionModel guardada =
                notificacionRepository.save(
                        notificacionModel);

        log.info("Notificacion creada {}",
                guardada.getId());

        return convertirDTO(guardada);
        }


        // Eliminar
        @Override
        public void eliminarNotificacion(Long id) {

                log.info("Eliminando notificacion {}", id);

                NotificacionModel notificacion = notificacionRepository.findById(id)
                                .orElseThrow(() ->new ResourceNotFoundException("Notificacion no encontrada"));

                notificacionRepository.delete(notificacion);
        }

}
