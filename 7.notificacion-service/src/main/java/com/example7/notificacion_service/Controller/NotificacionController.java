package com.example7.notificacion_service.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example7.notificacion_service.DTO.NotificacionRequestDTO;
import com.example7.notificacion_service.DTO.NotificacionResponseDTO;
import com.example7.notificacion_service.Service.NotificacionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionService notificacionService;

    // GET todas
    @GetMapping
    public ResponseEntity<List<NotificacionResponseDTO>> listar() {

        return ResponseEntity.ok(notificacionService.obtenerTodas());
    }

    // GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<NotificacionResponseDTO> obtenerPorId(@PathVariable Long id) {

        return ResponseEntity.ok(notificacionService.obtenerPorId(id));
    }

    // GET por usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<NotificacionResponseDTO>> obtenerPorUsuario(@PathVariable Long usuarioId) {

        return ResponseEntity.ok(notificacionService
                        .obtenerPorUsuario(usuarioId));
    }

    // GET por pedido
    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<NotificacionResponseDTO>> obtenerPorPedido(@PathVariable Long pedidoId) {

        return ResponseEntity.ok(notificacionService
                        .obtenerPorPedido(pedidoId));
    }

    // POST
    @PostMapping
    public ResponseEntity<NotificacionResponseDTO> crear(@Valid @RequestBody NotificacionRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(notificacionService.crearNotificacion(dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {

        notificacionService.eliminarNotificacion(id);

        return ResponseEntity.ok("Notificacion eliminada");
    }

}
