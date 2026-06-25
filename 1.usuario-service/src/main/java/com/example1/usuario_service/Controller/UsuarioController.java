package com.example1.usuario_service.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example1.usuario_service.DTO.LoginDTO;
import com.example1.usuario_service.DTO.UsuarioRequestDTO;
import com.example1.usuario_service.DTO.UsuarioResponseDTO;
import com.example1.usuario_service.Service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    // GET todos
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {

        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    // GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerPorId(@PathVariable Long id) {

        return ResponseEntity.ok(usuarioService.obtenerPorId(id));
    }

    // POST crear usuario
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crear(@Valid @RequestBody UsuarioRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioService.crearUsuario(dto));
    }

    // PUT actualizar
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO dto) {

        return ResponseEntity.ok(
                usuarioService.actualizarUsuario(id,dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {

        usuarioService.eliminarUsuario(id);

        return ResponseEntity.ok(
                "Usuario eliminado");
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO dto) {

        return ResponseEntity.ok(usuarioService.login(dto));
    }


    
}