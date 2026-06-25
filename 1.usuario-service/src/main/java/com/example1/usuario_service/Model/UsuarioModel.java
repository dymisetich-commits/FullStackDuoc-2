package com.example1.usuario_service.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class UsuarioModel {
    // ID usuario
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    // Correo
    @Email(message = "Correo inválido")
    @NotBlank(message = "El correo es obligatorio")
    @Column(unique = true)
    private String correo;

    // Contraseña
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    // Rol
    // CLIENTE / ADMIN
    private String rol;
}