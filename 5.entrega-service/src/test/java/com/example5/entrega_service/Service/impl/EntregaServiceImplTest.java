package com.example5.entrega_service.Service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
    
import java.time.LocalDateTime;
import java.util.Optional;
    
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
    
import com.example5.entrega_service.DTO.EntregaRequestDTO;
import com.example5.entrega_service.DTO.EntregaResponseDTO;
import com.example5.entrega_service.DTO.PedidoDTO;
import com.example5.entrega_service.Feign.PedidoFeignClient;
import com.example5.entrega_service.Model.EntregaModel;
import com.example5.entrega_service.Repository.EntregaRepository;
import com.example5.entrega_service.Service.Impl.EntregaServiceImpl;
    
@ExtendWith(MockitoExtension.class)
public class EntregaServiceImplTest {
    @Mock
    private EntregaRepository entregaRepository;

    @Mock
    private PedidoFeignClient pedidoFeignClient;

    @InjectMocks
    private EntregaServiceImpl entregaService;

    private EntregaModel entregaModel;
    private EntregaRequestDTO requestDTO;
    private PedidoDTO pedidoDTO; // <--- Agregamos el DTO correcto

    @BeforeEach
    void setUp() {
        // Configuración de la Entrega
        entregaModel = new EntregaModel();
        entregaModel.setId(1L);
        entregaModel.setPedidoId(100L);
        entregaModel.setRepartidor("Juan Pérez");
        entregaModel.setEstado("PENDIENTE");
        entregaModel.setDireccion("Calle Falsa 123");
        entregaModel.setFechaEntrega(LocalDateTime.now());

        // Configuración del Request
        requestDTO = new EntregaRequestDTO();
        requestDTO.setPedidoId(100L);
        requestDTO.setRepartidor("Juan Pérez");
        requestDTO.setDireccion("Calle Falsa 123");

        // Configuración del Pedido ficticio (Ajusta los setters según los campos reales de tu PedidoDTO)
        pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(100L); 
    }

    // PRUEBA 1: Verificar que busca correctamente una entrega por su ID
    @Test
    void testObtenerPorId_Exitoso() {
        // Arrange
        when(entregaRepository.findById(1L)).thenReturn(Optional.of(entregaModel));

        // Act
        EntregaResponseDTO resultado = entregaService.obtenerPorId(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(entregaRepository, times(1)).findById(1L);
    }

    // PRUEBA 2: Verificar la creación usando el PedidoDTO correcto para Feign
    @Test
    void testCrearEntrega() {
        // Arrange
        // Ahora sí calza perfectamente: devolverá un PedidoDTO cuando Feign lo solicite
        when(pedidoFeignClient.obtenerPedido(100L)).thenReturn(pedidoDTO); 
        when(entregaRepository.save(any(EntregaModel.class))).thenReturn(entregaModel);

        // Act
        EntregaResponseDTO resultado = entregaService.crearEntrega(requestDTO);

        // Assert
        assertNotNull(resultado);
        assertEquals("PENDIENTE", resultado.getEstado());
        verify(pedidoFeignClient, times(1)).obtenerPedido(100L);
        verify(entregaRepository, times(1)).save(any(EntregaModel.class));
    }
}


