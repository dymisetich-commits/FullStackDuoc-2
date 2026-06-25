package com.example4.pago_service.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PagoRequestDTO {

    @NotNull(message = "El pedido es obligatorio")
    private Long pedidoId;

    @NotNull(message = "El monto es obligatorio")
    private Double monto;

    @NotBlank(message = "El metodo de pago es obligatorio")
    private String metodoPago;
}