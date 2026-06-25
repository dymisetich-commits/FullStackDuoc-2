package com.example1.usuario_service.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example1.usuario_service.Model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    // Buscar usuario por correo
    Optional<UsuarioModel> findByCorreo(String correo);
}
