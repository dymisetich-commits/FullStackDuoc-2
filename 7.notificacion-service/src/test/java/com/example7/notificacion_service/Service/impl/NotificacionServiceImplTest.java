package com.example7.notificacion_service.Service.impl;

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

import com.example7.notificacion_service.DTO.NotificacionRequestDTO;
import com.example7.notificacion_service.DTO.NotificacionResponseDTO;
import com.example7.notificacion_service.DTO.PedidoDTO;
import com.example7.notificacion_service.DTO.UsuarioDTO;
import com.example7.notificacion_service.Feign.PedidoFeignClient;
import com.example7.notificacion_service.Feign.UsuarioFeignClient;
import com.example7.notificacion_service.Model.NotificacionModel;
import com.example7.notificacion_service.Repository.NotificacionRepository;
import com.example7.notificacion_service.Service.Impl.NotificacionServiceImpl;


@ExtendWith(MockitoExtension.class)
public class NotificacionServiceImplTest {
    @Mock
    private NotificacionRepository notificacionRepository;

    @Mock
    private UsuarioFeignClient usuarioFeignClient;

    @Mock
    private PedidoFeignClient pedidoFeignClient;

    @InjectMocks
    private NotificacionServiceImpl notificacionService;

    private NotificacionModel notificacionModel;
    private NotificacionRequestDTO requestDTO;
    private UsuarioDTO usuarioDTO;
    private PedidoDTO pedidoDTO;

    @BeforeEach
    void setUp() {
        // Inicializar entidad base de notificación
        notificacionModel = new NotificacionModel();
        notificacionModel.setId(1L);
        notificacionModel.setUsuarioId(10L);
        notificacionModel.setPedidoId(100L);
        notificacionModel.setTipo("EMAIL");
        notificacionModel.setMensaje("Tu pedido está en camino");
        notificacionModel.setEstado("ENVIADA");
        notificacionModel.setFecha(LocalDateTime.now());

        // Inicializar objeto Request de entrada
        requestDTO = new NotificacionRequestDTO();
        requestDTO.setUsuarioId(10L);
        requestDTO.setPedidoId(100L);
        requestDTO.setTipo("EMAIL");
        requestDTO.setMensaje("Tu pedido está en camino");

        // Inicializar DTOs correctos para simular las llamadas externas de Feign
        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNombre("Ana López");

        pedidoDTO = new PedidoDTO();
        pedidoDTO.setId(100L);
    }

    // PRUEBA 1: Verificar el comportamiento correcto al buscar por ID existente
    @Test
    void testObtenerPorId_Exitoso() {
        // Arrange
        when(notificacionRepository.findById(1L)).thenReturn(Optional.of(notificacionModel));

        // Act
        NotificacionResponseDTO resultado = notificacionService.obtenerPorId(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("ENVIADA", resultado.getEstado());
        verify(notificacionRepository, times(1)).findById(1L);
    }

    // PRUEBA 2: Verificar la creación correcta de la notificación con validación Feign
    @Test
    void testCrearNotificacion_Exitoso() {
        // Arrange
        when(usuarioFeignClient.obtenerUsuario(10L)).thenReturn(usuarioDTO);
        when(pedidoFeignClient.obtenerPedido(100L)).thenReturn(pedidoDTO);
        when(notificacionRepository.save(any(NotificacionModel.class))).thenReturn(notificacionModel);

        // Act
        NotificacionResponseDTO resultado = notificacionService.crearNotificacion(requestDTO);

        // Assert
        assertNotNull(resultado);
        assertEquals(10L, resultado.getUsuarioId());
        assertEquals("ENVIADA", resultado.getEstado());
        
        // Comprobar interacciones con servicios externos y base de datos
        verify(usuarioFeignClient, times(1)).obtenerUsuario(10L);
        verify(pedidoFeignClient, times(1)).obtenerPedido(100L);
        verify(notificacionRepository, times(1)).save(any(NotificacionModel.class));
    }

}
