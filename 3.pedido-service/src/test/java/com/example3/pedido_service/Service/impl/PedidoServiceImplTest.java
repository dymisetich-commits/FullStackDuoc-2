package com.example3.pedido_service.Service.impl;

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

import com.example3.pedido_service.Dto.RestauranteDTO;
import com.example3.pedido_service.Dto.UsuarioDTO;
import com.example3.pedido_service.Model.PedidoModel;
import com.example3.pedido_service.Repository.PedidoRepository;
import com.example3.pedido_service.Service.Impl.PedidoServiceImpl;
import com.example3.pedido_service.feign.RestauranteFeignClient;
import com.example3.pedido_service.feign.UsuarioFeignClient;

@ExtendWith(MockitoExtension.class)
class PedidoServiceImplTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private UsuarioFeignClient usuarioFeignClient;

    @Mock
    private RestauranteFeignClient restauranteFeignClient;

    @InjectMocks
    private PedidoServiceImpl pedidoService;

    @Test
    void buscarPedidoPorId() {

        PedidoModel pedido = new PedidoModel();

        pedido.setId(1L);
        pedido.setUsuarioId(1L);
        pedido.setRestauranteId(1L);
        pedido.setEstado("PENDIENTE");
        pedido.setTotal(15000.0);

        when(pedidoRepository.findById(1L))
                .thenReturn(Optional.of(pedido));

        PedidoModel resultado =
                pedidoService.findById(1L);

        assertEquals(1L, resultado.getId());
        assertEquals("PENDIENTE", resultado.getEstado());
    }

    @Test
    void crearPedidoDebeGuardarCorrectamente() {

        PedidoModel pedido = new PedidoModel();

        pedido.setUsuarioId(1L);
        pedido.setRestauranteId(1L);
        pedido.setTotal(20000.0);

        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setNombre("Patricio");

        RestauranteDTO restaurante = new RestauranteDTO();
        restaurante.setNombre("McDonalds");

        when(usuarioFeignClient.obtenerUsuario(1L))
                .thenReturn(usuario);

        when(restauranteFeignClient.obtenerRestaurante(1L))
                .thenReturn(restaurante);

        when(pedidoRepository.save(any()))
                .thenReturn(pedido);

        PedidoModel resultado =
                pedidoService.save(pedido);

        assertEquals(20000.0, resultado.getTotal());

        verify(pedidoRepository).save(any());
    }

    @Test
    void eliminarPedidoDebeEliminarCorrectamente() {

        PedidoModel pedido = new PedidoModel();

        pedido.setId(1L);

        when(pedidoRepository.findById(1L))
                .thenReturn(Optional.of(pedido));

        pedidoService.delete(1L);

        verify(pedidoRepository).delete(pedido);
    }
}