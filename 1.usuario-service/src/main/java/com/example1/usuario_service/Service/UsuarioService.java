package com.example1.usuario_service.Service;

import java.util.List;

import com.example1.usuario_service.DTO.LoginDTO;
import com.example1.usuario_service.DTO.UsuarioRequestDTO;
import com.example1.usuario_service.DTO.UsuarioResponseDTO;

public interface UsuarioService {


    // Obtener todos
    List<UsuarioResponseDTO> obtenerTodos();

    // Obtener por ID
    UsuarioResponseDTO obtenerPorId(Long id);

    // Crear usuario
    UsuarioResponseDTO crearUsuario(UsuarioRequestDTO dto);

    // Actualizar usuario
    UsuarioResponseDTO actualizarUsuario(Long id,UsuarioRequestDTO dto);

    // Eliminar usuario
    void eliminarUsuario(Long id);

    // Login simple
    String login(LoginDTO dto);
}
