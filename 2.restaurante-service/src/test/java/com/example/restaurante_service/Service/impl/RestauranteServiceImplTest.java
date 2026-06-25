package com.example.restaurante_service.Service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.restaurante_service.Dto.RestauranteRequestDTO;
import com.example.restaurante_service.Dto.RestauranteResponseDTO;
import com.example.restaurante_service.Model.RestauranteModelo;
import com.example.restaurante_service.Repository.RestauranteRepository;

@ExtendWith(MockitoExtension.class)
public class RestauranteServiceImplTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    @InjectMocks
    private RestauranteServiceImpl restauranteService;

    @Test
    void buscarRestaurantePorId() {

        RestauranteModelo restaurante =
                new RestauranteModelo(
                        1L,
                        "McDonalds",
                        "Santiago",
                        "123456",
                        "Fast Food",
                        true);

        when(restauranteRepository.findById(1L))
                .thenReturn(Optional.of(restaurante));

        RestauranteResponseDTO resultado =
                restauranteService.findById(1L);

        assertEquals("McDonalds", resultado.getNombre());
    }

    @Test
    void guardarRestaurante() {

        RestauranteRequestDTO dto =
                new RestauranteRequestDTO();

        dto.setNombre("Burger King");
        dto.setDireccion("Providencia");
        dto.setTelefono("999999");
        dto.setTipoComida("Hamburguesas");
        dto.setActivo(true);

        RestauranteModelo guardado =
                new RestauranteModelo(
                        1L,
                        dto.getNombre(),
                        dto.getDireccion(),
                        dto.getTelefono(),
                        dto.getTipoComida(),
                        dto.getActivo());

        when(restauranteRepository.save(any()))
                .thenReturn(guardado);

        RestauranteResponseDTO resultado =
                restauranteService.save(dto);

        assertEquals("Burger King", resultado.getNombre());

        verify(restauranteRepository).save(any());
    }

    @Test
    void listarRestaurantes() {

        RestauranteModelo r1 =
                new RestauranteModelo(
                        1L,
                        "McDonalds",
                        "Centro",
                        "111",
                        "Fast Food",
                        true);

        RestauranteModelo r2 =
                new RestauranteModelo(
                        2L,
                        "KFC",
                        "Centro",
                        "222",
                        "Pollo",
                        true);

        when(restauranteRepository.findAll())
                .thenReturn(List.of(r1, r2));

        List<RestauranteResponseDTO> resultado =
                restauranteService.findAll();

        assertEquals(2, resultado.size());
    }
}