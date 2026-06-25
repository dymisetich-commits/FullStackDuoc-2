package com.example.restaurante_service.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// Lombok por los getters y setters
@Data

public class RestauranteRequestDTO {
    @NotBlank(message = "Nombre obligatorio")
    private String nombre;

    // Dirección obligatoria
    @NotBlank(message = "Direccion obligatoria")
    private String direccion;

    // Teléfono obligatorio
    @NotBlank(message = "Telefono obligatorio")
    private String telefono;

    // Tipo comida obligatorio
    @NotBlank(message = "Tipo comida obligatorio")
    private String tipoComida;

    // Estado obligatorio
    @NotNull(message = "Activo obligatorio")
    private Boolean activo;
}
