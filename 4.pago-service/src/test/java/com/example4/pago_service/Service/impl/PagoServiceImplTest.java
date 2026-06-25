package com.example4.pago_service.Service.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example4.pago_service.DTO.PagoRequestDTO;
import com.example4.pago_service.DTO.PagoResponseDTO;
import com.example4.pago_service.DTO.PedidoDTO;
import com.example4.pago_service.Feign.PedidoFeignClient;
import com.example4.pago_service.Model.PagoModel;
import com.example4.pago_service.Repository.PagoRepository;
import com.example4.pago_service.Service.Impl.PagoServiceImpl;

@ExtendWith(MockitoExtension.class)
class PagoServiceImplTest {

    @Mock
    private PagoRepository pagoRepository;

    @Mock
    private PedidoFeignClient pedidoFeignClient;

    @InjectMocks
    private PagoServiceImpl pagoService;

    @Test
    void obtenerPagoPorId() {

        PagoModel pago = new PagoModel();

        pago.setId(1L);
        pago.setPedidoId(10L);
        pago.setMonto(15000.0);
        pago.setEstado("PAGADO");

        when(pagoRepository.findById(1L))
                .thenReturn(Optional.of(pago));

        PagoResponseDTO resultado =
                pagoService.obtenerPorId(1L);

        assertEquals(1L, resultado.getId());
        assertEquals("PAGADO", resultado.getEstado());
    }

    @Test
    void crearPagoDebeGuardarCorrectamente() {

        PagoRequestDTO dto = new PagoRequestDTO();

        dto.setPedidoId(10L);
        dto.setMonto(20000.0);
        dto.setMetodoPago("TARJETA");

        PedidoDTO pedido = new PedidoDTO();
        pedido.setId(10L);

        PagoModel pagoGuardado = new PagoModel();

        pagoGuardado.setId(1L);
        pagoGuardado.setPedidoId(10L);
        pagoGuardado.setMonto(20000.0);
        pagoGuardado.setMetodoPago("TARJETA");
        pagoGuardado.setEstado("PAGADO");

        when(pedidoFeignClient.obtenerPedido(10L))
                .thenReturn(pedido);

        when(pagoRepository.save(any()))
                .thenReturn(pagoGuardado);

        PagoResponseDTO resultado =
                pagoService.crearPago(dto);

        assertEquals(20000.0, resultado.getMonto());

        verify(pagoRepository).save(any());
    }
}