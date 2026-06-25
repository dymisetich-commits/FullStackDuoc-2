package com.example6.historial_service.DTO;


import java.time.LocalDateTime;

import lombok.Data;

@Data
public class HistorialResponseDTO {

     private Long id;

    private Long usuarioId;

    private Long pedidoId;

    private String evento;

    private String descripcion;

    private LocalDateTime fecha;

}