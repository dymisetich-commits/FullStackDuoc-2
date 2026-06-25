package com.example4.pago_service.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example4.pago_service.DTO.PedidoDTO;

@FeignClient(name = "PEDIDO-SERVICE")
public interface PedidoFeignClient {

    @GetMapping("/api/pedidos/{id}")
    PedidoDTO obtenerPedido(@PathVariable Long id);
}
