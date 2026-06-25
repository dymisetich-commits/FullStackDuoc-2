package com.example.sucursal_service.DTO;

import lombok.Data;

@Data
public class SucursalRequestDTO {
    private String nombre;

    private String direccion;

    private String telefono;

    private String horario;
}
