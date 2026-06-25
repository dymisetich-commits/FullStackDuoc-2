package com.example7.notificacion_service.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PedidoDTO {

     private Long id;

    private Long usuarioId;

    private Long restauranteId;

    private Double total;

    private String estado;

    private LocalDateTime fecha;
}
