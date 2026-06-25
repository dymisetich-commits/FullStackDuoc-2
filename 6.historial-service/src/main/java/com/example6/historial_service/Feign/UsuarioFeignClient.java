package com.example6.historial_service.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example6.historial_service.DTO.UsuarioDTO;

@FeignClient(name = "USUARIO-SERVICE")
public interface UsuarioFeignClient {

    @GetMapping("/api/usuarios/{id}")
    UsuarioDTO obtenerUsuario(
            @PathVariable Long id);
}