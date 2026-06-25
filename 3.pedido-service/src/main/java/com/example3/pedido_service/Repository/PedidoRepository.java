package com.example3.pedido_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example3.pedido_service.Model.PedidoModel;

public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {



}