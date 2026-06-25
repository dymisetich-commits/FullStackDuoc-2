package com.example.sucursal_service.Service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.sucursal_service.DTO.SucursalRequestDTO;
import com.example.sucursal_service.DTO.SucursalResponseDTO;
import com.example.sucursal_service.Model.SucursalModel;
import com.example.sucursal_service.Repository.SucursalRepository;
import com.example.sucursal_service.Service.Impl.SucursalServiceImpl;

@ExtendWith(MockitoExtension.class)
public class SucursalServiceImplTest {

    @Mock
    private SucursalRepository sucursalRepository;

    @InjectMocks
    private SucursalServiceImpl sucursalService;

    private SucursalModel sucursalModel;
    private SucursalRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        // Modelo simulado de la base de datos
        sucursalModel = new SucursalModel();
        sucursalModel.setId(1L);
        sucursalModel.setNombre("Sucursal Central");
        sucursalModel.setDireccion("Av. Principal 456");

        // DTO de entrada para la creación
        requestDTO = new SucursalRequestDTO();
        requestDTO.setNombre("Sucursal Central");
        requestDTO.setDireccion("Av. Principal 456");
    }

    // PRUEBA 1: Verificar que una sucursal se crea correctamente
    @Test
    void testCrearSucursal_Exitoso() {
        // Arrange
        when(sucursalRepository.save(any(SucursalModel.class))).thenReturn(sucursalModel);

        // Act
        SucursalResponseDTO resultado = sucursalService.crearSucursal(requestDTO);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Sucursal Central", resultado.getNombre());
        verify(sucursalRepository, times(1)).save(any(SucursalModel.class));
    }

    // PRUEBA 2: Verificar la excepción correcta si la sucursal no existe al buscar por ID
    @Test
    void testObtenerPorId_NoEncontrado() {
        // Arrange
        when(sucursalRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> {
            sucursalService.obtenerPorId(99L);
        });

        verify(sucursalRepository, times(1)).findById(99L);
    }
}
