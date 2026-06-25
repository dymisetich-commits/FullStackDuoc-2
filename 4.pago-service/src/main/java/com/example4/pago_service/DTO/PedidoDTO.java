package com.example4.pago_service.DTO;
import lombok.Data;
import java.time.LocalDateTime;
@Data



public class PedidoDTO {

     private Long id;

    private Long usuarioId;

    private Long restauranteId;

    private Double total;

    private String estado;

    private LocalDateTime fecha;
}
