package com.example3.pedido_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example3.pedido_service.Dto.UsuarioDTO;


@FeignClient(name = "USUARIO-SERVICE")
public interface UsuarioFeignClient {

     @GetMapping("/api/usuarios/{id}")
    UsuarioDTO obtenerUsuario(@PathVariable Long id);

}
