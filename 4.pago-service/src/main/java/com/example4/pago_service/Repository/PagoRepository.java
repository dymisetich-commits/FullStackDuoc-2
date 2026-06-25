package com.example4.pago_service.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example4.pago_service.Model.PagoModel;

public interface PagoRepository extends JpaRepository<PagoModel, Long> {

    //Buscar pagos por pedidos
    List<PagoModel> findByPedidoId(Long pedidoId);

    //Buscar pagos por estado
    List<PagoModel> findByEstado(String estado);
}


