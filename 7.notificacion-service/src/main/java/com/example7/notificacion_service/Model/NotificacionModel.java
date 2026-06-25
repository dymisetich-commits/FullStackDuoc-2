package com.example7.notificacion_service.Model;

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
@Table(name = "notificaciones")
public class NotificacionModel {

    // ID notificación
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Usuario relacionado
    @NotNull(message = "El usuario es obligatorio")
    private Long usuarioId;

    // Pedido relacionado
    private Long pedidoId;

    // Tipo notificación
    @NotBlank(message = "El tipo es obligatorio")
    private String tipo;

    // Mensaje
    @NotBlank(message = "El mensaje es obligatorio")
    private String mensaje;

    // Estado
    private String estado;

    // Fecha envío
    private LocalDateTime fecha;
}
