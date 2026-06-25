package com.example3.pedido_service.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PedidoDTO {

    //el usuario realiza el pedido
    @NotNull(message = "El usuario es obligatorio")
    private Long usuarioId;

    //restaurante asociado
    @NotNull(message = "El restaurante es obligatorio")
    private Long restauranteId;

    //estadoactual del pedido
    private String estado;

    //total del pedido
    @Positive(message = "El total debe ser mayor a 0")
    private Double total;


}