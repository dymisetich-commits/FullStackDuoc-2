package com.example.sucursal_service.DTO;

import lombok.Data;

@Data
public class SucursalResponseDTO {
    private Long id;

    private String nombre;

    private String direccion;

    private String telefono;

    private String horario;
}
