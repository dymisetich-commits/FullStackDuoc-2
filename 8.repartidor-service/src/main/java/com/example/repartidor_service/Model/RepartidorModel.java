package com.example.repartidor_service.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "repartidores")
public class RepartidorModel {

    // ID repartidor
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre repartidor
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    // Teléfono
    @NotBlank(message = "El telefono es obligatorio")
    private String telefono;

    // Estado repartidor
    // DISPONIBLE / OCUPADO / EN_RUTA
    private String estado;

    // Vehículo
    private String vehiculo;
}