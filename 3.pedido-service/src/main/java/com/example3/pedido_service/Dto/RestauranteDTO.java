package com.example3.pedido_service.Dto;

import lombok.Data;

@Data
public class RestauranteDTO {

    private Long id;

    private String nombre;

    private String direccion;

    private String tipoComida;
}
