package com.example1.usuario_service.DTO;

import lombok.Data;

@Data
public class UsuarioResponseDTO {

    private Long id;

    private String nombre;

    private String correo;

    private String rol;
}
