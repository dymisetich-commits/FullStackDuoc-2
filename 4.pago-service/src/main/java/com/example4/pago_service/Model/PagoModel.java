package com.example4.pago_service.Model;

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
@Table(name = "pagos")
public class PagoModel {

    // ID del pago
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ID del pedido asociado
    @NotNull(message = "El pedido es obligatorio")
    private Long pedidoId;

    // Monto pagado
    @NotNull(message = "El monto es obligatorio")
    private Double monto;

    // Método de pago
    @NotBlank(message = "El metodo de pago es obligatorio")
    private String metodoPago;

    // Estado del pago
    // EJ: PENDIENTE, PAGADO, RECHAZADO
    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    // Fecha del pago
    private LocalDateTime fechaPago;
    
}
