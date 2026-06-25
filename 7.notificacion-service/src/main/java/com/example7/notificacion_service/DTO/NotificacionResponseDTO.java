package com.example7.notificacion_service.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NotificacionResponseDTO {

    private Long id;
    private Long usuarioId;
    private Long pedidoId;
    private String tipo;
    private String mensaje;
    private String estado;
    private LocalDateTime fecha;
}
