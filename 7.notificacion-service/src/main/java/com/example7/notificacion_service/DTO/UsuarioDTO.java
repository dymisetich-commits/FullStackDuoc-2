package com.example7.notificacion_service.DTO;

import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;

    private String nombre;

    private String correo;

    private String rol;
}