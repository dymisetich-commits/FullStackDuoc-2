package com.example5.entrega_service.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EntregaResponseDTO {

     private Long id;

    private Long pedidoId;

    private String repartidor;

    private String estado;

    private String direccion;

    private LocalDateTime fechaEntrega;
}
