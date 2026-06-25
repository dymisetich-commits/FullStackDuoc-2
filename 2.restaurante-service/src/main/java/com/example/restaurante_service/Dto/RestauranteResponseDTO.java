package com.example.restaurante_service.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

// Lombok por los getters/setters
@Data

//constructor completos
@AllArgsConstructor

public class RestauranteResponseDTO {

    //Dtos que se enviaran al cliente 
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String tipoComida;
    private boolean activo;
}

