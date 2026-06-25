package com.example.Categoria_service.Service.impl;

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

import com.example.Categoria_service.DTO.CategoriaRequestDTO;
import com.example.Categoria_service.DTO.CategoriaResponseDTO;
import com.example.Categoria_service.Model.CategoriaModel;
import com.example.Categoria_service.Repository.CategoriaRepository;
import com.example.Categoria_service.Service.Impl.CategoriaServiceImpl;


@ExtendWith(MockitoExtension.class)
public class CategoriaServiceImplTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaServiceImpl categoriaService;

    private CategoriaModel categoriaModel;
    private CategoriaRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        // Modelo simulado que devolvería la base de datos
        categoriaModel = new CategoriaModel();
        categoriaModel.setId(1L);
        categoriaModel.setNombre("Electrónica");
        categoriaModel.setDescripcion("Dispositivos y gadgets electrónicos");

        // DTO con los datos de entrada para la creación/actualización
        requestDTO = new CategoriaRequestDTO();
        requestDTO.setNombre("Electrónica");
        requestDTO.setDescripcion("Dispositivos y gadgets electrónicos");
    }

    // PRUEBA 1: Verificar que una categoría se crea correctamente mapeando el DTO a la Entidad
    @Test
    void testCrearCategoria_Exitoso() {
        // Arrange
        when(categoriaRepository.save(any(CategoriaModel.class))).thenReturn(categoriaModel);

        // Act
        CategoriaResponseDTO resultado = categoriaService.crearCategoria(requestDTO);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Electrónica", resultado.getNombre());
        verify(categoriaRepository, times(1)).save(any(CategoriaModel.class));
    }

    // PRUEBA 2: Verificar que se lanza la excepción correcta si el ID buscado no existe
    @Test
    void testObtenerPorId_NoEncontrado() {
        // Arrange
        when(categoriaRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> {
            categoriaService.obtenerPorId(99L);
        });

        verify(categoriaRepository, times(1)).findById(99L);
    }
}
