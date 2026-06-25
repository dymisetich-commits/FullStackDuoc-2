package com.example3.pedido_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example3.pedido_service.Dto.RestauranteDTO;


@FeignClient(name = "RESTAURANTE-SERVICE")
public interface RestauranteFeignClient {

    @GetMapping("/api/restaurantes/{id}")
    RestauranteDTO obtenerRestaurante(@PathVariable Long id);
}
