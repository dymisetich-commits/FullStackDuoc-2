package com.example5.entrega_service.Service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.example5.entrega_service.DTO.EntregaRequestDTO;
import com.example5.entrega_service.DTO.EntregaResponseDTO;
import com.example5.entrega_service.Feign.PedidoFeignClient;
import com.example5.entrega_service.Model.EntregaModel;
import com.example5.entrega_service.Repository.EntregaRepository;
import com.example5.entrega_service.Service.EntregaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor //genera constructor automático para atributos final
public class EntregaServiceImpl implements EntregaService {

        private final PedidoFeignClient pedidoFeignClient;
        //convertir entidad a DTO
        private EntregaResponseDTO convertirDTO(EntregaModel entregaModel){
                
                EntregaResponseDTO dto = new EntregaResponseDTO();

                dto.setId(entregaModel.getId());
                dto.setPedidoId(entregaModel.getPedidoId());
                dto.setRepartidor(entregaModel.getRepartidor());
                dto.setEstado(entregaModel.getEstado());
                dto.setDireccion(entregaModel.getDireccion());
                dto.setFechaEntrega(entregaModel.getFechaEntrega());

                return dto;
        }

        //lo que hacemos siemrpe 
        private final EntregaRepository entregaRepository;

        //listar entregas
        @Override
        public List<EntregaResponseDTO> obtenerTodas(){
                log.info("Listando entregas");

                return entregaRepository.findAll().stream().map(this::convertirDTO).toList();
        }
        //buscar por id 
        @Override
        public EntregaResponseDTO obtenerPorId(Long id){
                log.info("Buscando entrega {}", id);
                EntregaModel entregaModel= entregaRepository.findById(id).orElseThrow(()-> new NoSuchElementException("ENTREGA no encontrada"));

                return convertirDTO(entregaModel);
        }

       @Override
public EntregaResponseDTO crearEntrega(
        EntregaRequestDTO dto){

    log.info("CREANDO ENTREGA");

    // validar pedido
    var pedido =
            pedidoFeignClient.obtenerPedido(
                    dto.getPedidoId());

    log.info("Pedido encontrado {}",
            pedido.getId());

    EntregaModel entregaModel =
            new EntregaModel();

    entregaModel.setPedidoId(dto.getPedidoId());

    entregaModel.setRepartidor(
            dto.getRepartidor());

    entregaModel.setDireccion(
            dto.getDireccion());

    entregaModel.setEstado("PENDIENTE");

    entregaModel.setFechaEntrega(
            LocalDateTime.now());

    EntregaModel guardada =
            entregaRepository.save(
                    entregaModel);

    log.info("Entrega creada {}",
            guardada.getId());

    return convertirDTO(guardada);
}

        //eliminar entrega
        @Override
        public void eliminarEntrega(Long id){
                log.info("eliminando entrega {}", id);

                EntregaModel entrega = entregaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Entrega no encontrada"));
                
                entregaRepository.delete(entrega);
        }
   
}
