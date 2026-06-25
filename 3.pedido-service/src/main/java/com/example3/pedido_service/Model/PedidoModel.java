package com.example3.pedido_service.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //genera getters, setters y toString automaticamente
@AllArgsConstructor //constructor con todos los atributos
@NoArgsConstructor //constructor vacio
@Entity //indica que es una entidad de la BD
@Table(name = "pedido")

public class PedidoModel {

    //id autoincremental osea que cada vez se incremental al crear un id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     //id del usuario que realiza el pedido
    @NotNull(message = "El usuario es obligatorio")
    private Long usuarioId;

    //id del restaurante asociado
    @NotNull(message = "El restaurante es obligatorio")
    private Long restauranteId;

    //fecha del pedido
    private LocalDateTime fecha;

    //estado del pedido
    //ejemplo: PENDIENTE, PAGADO, ENTREGADO
    private String estado;

    //monto total del pedido
    @Positive(message = "El total debe ser mayor a 0")
    private Double total;
}
