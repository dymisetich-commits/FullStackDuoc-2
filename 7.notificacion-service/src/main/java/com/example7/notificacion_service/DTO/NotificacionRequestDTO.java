package com.example7.notificacion_service.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NotificacionRequestDTO {

    @NotNull(message = "El usuario es obligatorio")
    private Long usuarioId;

    private Long pedidoId;

    @NotBlank(message = "El tipo es obligatorio")
    private String tipo;

    @NotBlank(message = "El mensaje es obligatorio")
    private String mensaje;
}