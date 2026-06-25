package com.example5.entrega_service.Model;

import java.time.LocalDateTime;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "entregas")
public class EntregaModel {

    // ID de la entrega
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Pedido relacionado
    @NotNull(message = "El pedido es obligatorio")
    private Long pedidoId;

    // Nombre del repartidor
    @NotBlank(message = "El repartidor es obligatorio")
    private String repartidor;

    // Estado entrega
    // PENDIENTE, EN_CAMINO, ENTREGADO
    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    // Dirección entrega
    @NotBlank(message = "La direccion es obligatoria")
    private String direccion;

    // Fecha creación
    private LocalDateTime fechaEntrega;
}
