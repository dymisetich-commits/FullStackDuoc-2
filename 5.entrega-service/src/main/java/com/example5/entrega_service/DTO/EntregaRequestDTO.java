package com.example5.entrega_service.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EntregaRequestDTO {

    @NotNull(message = "El pedido es obligatorio")
    private Long pedidoId;

    @NotBlank(message = "El repartidor es obligatorio")
    private String repartidor;

    @NotBlank(message = "La direccion es obligatoria")
    private String direccion;
    
}
