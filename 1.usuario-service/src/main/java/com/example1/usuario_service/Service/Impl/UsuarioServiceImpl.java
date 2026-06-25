package com.example1.usuario_service.Service.Impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.example1.usuario_service.DTO.LoginDTO;
import com.example1.usuario_service.DTO.UsuarioRequestDTO;
import com.example1.usuario_service.DTO.UsuarioResponseDTO;
import com.example1.usuario_service.Model.UsuarioModel;
import com.example1.usuario_service.Repository.UsuarioRepository;
import com.example1.usuario_service.Security.JwtUtil;
import com.example1.usuario_service.Service.UsuarioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

    //inyecto el siguente cod
    private final JwtUtil jwtUtil;

        // Convertir entidad a DTO
        private UsuarioResponseDTO convertirDTO(UsuarioModel usuario) {

        UsuarioResponseDTO dto = new UsuarioResponseDTO();

        dto.setId(usuario.getId());

        dto.setNombre(usuario.getNombre());

        dto.setCorreo(usuario.getCorreo());

        dto.setRol(usuario.getRol());

        return dto;
    }

        //lo de siempre
     private final UsuarioRepository usuarioRepository;

     // Obtener todos
    @Override
    public List<UsuarioResponseDTO> obtenerTodos() {

        log.info("Listando usuarios");

        return usuarioRepository.findAll()
                .stream()
                .map(this::convertirDTO)
                .toList();
    }

    // Obtener por ID
    @Override
    public UsuarioResponseDTO obtenerPorId(Long id) {

        log.info("Buscando usuario {}", id);

        UsuarioModel usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        return convertirDTO(usuario);
    }

    // Crear usuario
    @Override
    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO dto) {

        log.info("Creando usuario");

        UsuarioModel usuario =
                new UsuarioModel();

        usuario.setNombre(dto.getNombre());

        usuario.setCorreo(dto.getCorreo());

        usuario.setPassword(dto.getPassword());

        usuario.setRol("CLIENTE");

        UsuarioModel guardado = usuarioRepository.save(usuario);

        return convertirDTO(guardado);
    }

    // Actualizar usuario
    @Override
    public UsuarioResponseDTO actualizarUsuario(
            Long id,
            UsuarioRequestDTO dto) {

        log.info("Actualizando usuario {}", id);

        UsuarioModel usuario =
                usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        usuario.setNombre(dto.getNombre());

        usuario.setCorreo(dto.getCorreo());

        usuario.setPassword(dto.getPassword());

        usuario.setRol(dto.getRol());

        UsuarioModel actualizado = usuarioRepository.save(usuario);

        return convertirDTO(actualizado);
    }

    // Eliminar usuario
    @Override
    public void eliminarUsuario(Long id) {

        log.info("Eliminando usuario {}", id);

        UsuarioModel usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        usuarioRepository.delete(usuario);
    }

    @Override
    public String login(LoginDTO dto) {

        log.info("Intentando login");

    UsuarioModel usuario =
            usuarioRepository
                    .findByCorreo(dto.getCorreo())
                    .orElseThrow(() ->
                            new NoSuchElementException(
                                    "Correo no encontrado"));

    // validar password
    if (!usuario.getPassword()
            .equals(dto.getPassword())) {

        throw new RuntimeException(
                "Contraseña incorrecta");
    }

    // generar JWT
        return jwtUtil.generarToken(
            usuario.getCorreo());
}

    
}
