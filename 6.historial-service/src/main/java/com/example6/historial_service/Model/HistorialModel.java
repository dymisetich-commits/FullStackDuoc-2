package com.example6.historial_service.Model;

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
@Table(name = "historial")
public class HistorialModel {

    //id de historial
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Usuario relacionado
    @NotNull(message = "El usuario es obligatorio")
    private Long usuarioId;

    // Pedido relacionado
    private Long pedidoId;

    // Evento
    @NotBlank(message = "El evento es obligatorio")
    private String evento;

    // Descripción evento
    private String descripcion;

    // Fecha del evento
    private LocalDateTime fecha;
    
    
}