package com.example.repartidor_service.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RepartidorRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El telefono es obligatorio")
    private String telefono;

    private String estado;

    private String vehiculo;
}