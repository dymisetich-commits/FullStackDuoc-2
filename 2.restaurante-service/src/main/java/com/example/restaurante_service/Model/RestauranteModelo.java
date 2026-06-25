package com.example.restaurante_service.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "restaurante")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class RestauranteModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nombre obligatorio")
    private String nombre;

    @NotBlank(message = "Direccion obligatoria")
    private String direccion;

    @NotBlank(message = "Telefono obligatorio")
    private String telefono;

    @NotBlank(message = "Tipo comida obligatorio")
    private String tipoComida;

    @NotNull(message = "Activo obligatorio")
    private Boolean activo;
}
