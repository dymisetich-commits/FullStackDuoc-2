package com.example6.historial_service.Service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example6.historial_service.DTO.HistorialRequestDTO;
import com.example6.historial_service.DTO.HistorialResponseDTO;
import com.example6.historial_service.DTO.PedidoDTO;
import com.example6.historial_service.DTO.UsuarioDTO;
import com.example6.historial_service.Feign.PedidoFeignClient;
import com.example6.historial_service.Feign.UsuarioFeignClient;
import com.example6.historial_service.Model.HistorialModel;
import com.example6.historial_service.Repository.HistorialRepository;
import com.example6.historial_service.Service.Impl.HistorialServiceImpl;


@ExtendWith(MockitoExtension.class)
public class HistorialServiceImplTest {


    @Mock
    private HistorialRepository historialRepository;

    @Mock
    private UsuarioFeignClient usuarioFeignClient;

    @Mock
    private PedidoFeignClient pedidoFeignClient;

    @InjectMocks
    private HistorialServiceImpl historialService;

    private HistorialModel historialModel;
    private HistorialRequestDTO requestDTO;
    private UsuarioDTO usuarioDTO;  // <-- Declaramos el DTO correcto
    private PedidoDTO pedidoDTO;    // <-- Declaramos el DTO correcto

    @BeforeEach
    void setUp() {
        // Entidad base
        historialModel = new HistorialModel();
        historialModel.setId(1L);
        historialModel.setUsuarioId(10L);
        historialModel.setPedidoId(100L);
        historialModel.setEvento("LOGIN");
        historialModel.setDescripcion("El usuario inició sesión");
        historialModel.setFecha(LocalDateTime.now());

        // Request de entrada
        requestDTO = new HistorialRequestDTO();
        requestDTO.setUsuarioId(10L);
        requestDTO.setPedidoId(100L);
        requestDTO.setEvento("LOGIN");
        requestDTO.setDescripcion("El usuario inició sesión");

        // Inicializamos el UsuarioDTO real (Ajusta los métodos set según tus campos reales)
        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNombre("Carlos D.");

        // Inicializamos el PedidoDTO real (Ajusta los métodos set según tus campos reales)
        pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(100L);
    }

    // PRUEBA 1: Verificar que se obtiene correctamente un historial por su ID
    @Test
    void testObtenerPorId_Exitoso() {
        // Arrange
        when(historialRepository.findById(1L)).thenReturn(Optional.of(historialModel));

        // Act
        HistorialResponseDTO resultado = historialService.obtenerPorId(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(historialRepository, times(1)).findById(1L);
    }

    // PRUEBA 2: Verificar la creación del historial usando los DTOs correctos
    @Test
    void testCrearHistorial_Exitoso() {
        // Arrange
        // Ahora los tipos coinciden perfectamente con lo que esperan tus clientes Feign
        when(usuarioFeignClient.obtenerUsuario(10L)).thenReturn(usuarioDTO);
        when(pedidoFeignClient.obtenerPedido(100L)).thenReturn(pedidoDTO);
        when(historialRepository.save(any(HistorialModel.class))).thenReturn(historialModel);

        // Act
        HistorialResponseDTO resultado = historialService.crearHistorial(requestDTO);

        // Assert
        assertNotNull(resultado);
        assertEquals(10L, resultado.getUsuarioId());
        assertEquals(100L, resultado.getPedidoId());
        
        verify(usuarioFeignClient, times(1)).obtenerUsuario(10L);
        verify(pedidoFeignClient, times(1)).obtenerPedido(100L);
        verify(historialRepository, times(1)).save(any(HistorialModel.class));
    }
}

