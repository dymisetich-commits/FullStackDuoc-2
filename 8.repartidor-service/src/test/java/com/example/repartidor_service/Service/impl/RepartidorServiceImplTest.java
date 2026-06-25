package com.example.repartidor_service.Service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.repartidor_service.DTO.RepartidorRequestDTO;
import com.example.repartidor_service.DTO.RepartidorResponseDTO;
import com.example.repartidor_service.Model.RepartidorModel;
import com.example.repartidor_service.Repository.RepartidorRepository;
import com.example.repartidor_service.Service.Impl.RepartidorServiceImpl;


@ExtendWith(MockitoExtension.class)
public class RepartidorServiceImplTest {

    @Mock
    private RepartidorRepository repartidorRepository;

    @InjectMocks
    private RepartidorServiceImpl repartidorService;

    private RepartidorModel repartidorModel;
    private RepartidorRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        // Configuramos el modelo simulado de la base de datos
        repartidorModel = new RepartidorModel();
        repartidorModel.setId(1L);
        repartidorModel.setNombre("Marcos Pérez");
        repartidorModel.setTelefono("555-1234");
        repartidorModel.setVehiculo("Moto");
        repartidorModel.setEstado("DISPONIBLE");

        // Configuramos los datos que simulan venir del controlador
        requestDTO = new RepartidorRequestDTO();
        requestDTO.setNombre("Marcos Pérez");
        requestDTO.setTelefono("555-1234");
        requestDTO.setVehiculo("Moto");
        // Nota: En la creación el estado se fuerza a "DISPONIBLE" por código,
        // no hace falta mapearlo desde el DTO para el flujo de crear.
    }

    // PRUEBA 1: Verificar la búsqueda exitosa por ID
    @Test
    void testObtenerPorId_Exitoso() {
        // Arrange
        when(repartidorRepository.findById(1L)).thenReturn(Optional.of(repartidorModel));

        // Act
        RepartidorResponseDTO resultado = repartidorService.obtenerPorId(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Marcos Pérez", resultado.getNombre());
        verify(repartidorRepository, times(1)).findById(1L);
    }

    // PRUEBA 2: Verificar que se crea el repartidor y se le asigna el estado DISPONIBLE automáticamente
    @Test
    void testCrearRepartidor_Exitoso() {
        // Arrange
        when(repartidorRepository.save(any(RepartidorModel.class))).thenReturn(repartidorModel);

        // Act
        RepartidorResponseDTO resultado = repartidorService.crearRepartidor(requestDTO);

        // Assert
        assertNotNull(resultado);
        assertEquals("DISPONIBLE", resultado.getEstado());
        assertEquals("Marcos Pérez", resultado.getNombre());
        verify(repartidorRepository, times(1)).save(any(RepartidorModel.class));
    }
}
