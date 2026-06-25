package com.example4.pago_service.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PagoResponseDTO {

    private Long id;

    private Long pedidoId;

    private Double monto;

    private String metodoPago;

    private String estado;

    private LocalDateTime fechaPago;



}


