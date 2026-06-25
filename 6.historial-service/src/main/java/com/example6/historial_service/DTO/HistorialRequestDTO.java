package com.example6.historial_service.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HistorialRequestDTO {

    @NotNull(message = "El usuario es obligatorio")
    private Long usuarioId;

    private Long pedidoId;

    @NotBlank(message = "El evento es obligatorio")
    private String evento;

    private String descripcion;
}
