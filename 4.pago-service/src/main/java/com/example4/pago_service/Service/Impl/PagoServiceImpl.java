package com.example4.pago_service.Service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.example4.pago_service.DTO.PagoRequestDTO;
import com.example4.pago_service.DTO.PagoResponseDTO;
import com.example4.pago_service.Feign.PedidoFeignClient;
import com.example4.pago_service.Model.PagoModel;
import com.example4.pago_service.Repository.PagoRepository;
import com.example4.pago_service.Service.PagoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class PagoServiceImpl implements PagoService {

    // Convertir entidad a DTO
    private PagoResponseDTO convertirDTO(PagoModel pagoModel){
        
        PagoResponseDTO dto = new PagoResponseDTO();

        dto.setId(pagoModel.getId());
        dto.setPedidoId(pagoModel.getPedidoId());
        dto.setMonto(pagoModel.getMonto());
        dto.setMetodoPago(pagoModel.getMetodoPago());
        dto.setEstado(pagoModel.getEstado());
        dto.setFechaPago(pagoModel.getFechaPago());

        return dto;
    }


    private final PedidoFeignClient pedidoFeignClient;

    //lo de siempre
    private final PagoRepository pagoRepository;

    //listar pagos
    @Override
    public List<PagoResponseDTO> obtenerTodos(){
        log.info("Listando Pagos");

        return pagoRepository.findAll().stream().map(this::convertirDTO).toList();

    }

    //buscar por id
    @Override
    public PagoResponseDTO obtenerPorId(Long id){
        log.info("Buscando pago {}", id);

        PagoModel pago= pagoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Pago no encontrado"));
    
        return convertirDTO(pago);
    }

    @Override
public PagoResponseDTO crearPago(PagoRequestDTO dto){

    log.info("Creando pago");

    // validar pedido
    var pedido =
            pedidoFeignClient.obtenerPedido(
                    dto.getPedidoId());

    log.info("Pedido encontrado: {}",
            pedido.getId());

    PagoModel pagoModel = new PagoModel();

    pagoModel.setPedidoId(dto.getPedidoId());

    pagoModel.setMonto(dto.getMonto());

    pagoModel.setMetodoPago(dto.getMetodoPago());

    // estado inicial
    pagoModel.setEstado("PAGADO");

    // fecha automática
    pagoModel.setFechaPago(LocalDateTime.now());

    PagoModel guardado =
            pagoRepository.save(pagoModel);

    log.info("Pago guardado con ID {}",
            guardado.getId());

    return convertirDTO(guardado);
}


    //eliminar pago
    @Override 
    public void eliminarPago(Long id){
        log.info("Eliminando pago {}", id);

        PagoModel pagoModel = pagoRepository.findById(id).
                                orElseThrow(() -> new NoSuchElementException("Pago no encontrado"));
    

        pagoRepository.delete(pagoModel);

    }


}

