package com.example6.historial_service.Controller;

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

import com.example6.historial_service.DTO.HistorialRequestDTO;
import com.example6.historial_service.DTO.HistorialResponseDTO;
import com.example6.historial_service.Service.HistorialService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/historial")
@RequiredArgsConstructor

public class HistorialController {

    private final HistorialService historialService;

    // GET todos
    @GetMapping
    public ResponseEntity<List<HistorialResponseDTO>> listar() {

        return ResponseEntity.ok(historialService.obtenerTodos());
    }

    // GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<HistorialResponseDTO> obtenerPorId(@PathVariable Long id) {

        return ResponseEntity.ok(historialService.obtenerPorId(id));
    
}

    // GET por usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<HistorialResponseDTO>> obtenerPorUsuario(@PathVariable Long usuarioId) {

        return ResponseEntity.ok(historialService.obtenerPorUsuario(usuarioId));
    }

    // GET por pedido
    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<HistorialResponseDTO>>obtenerPorPedido(@PathVariable Long pedidoId) {

        return ResponseEntity.ok(historialService.obtenerPorPedido(pedidoId));
    }

    // POST
    @PostMapping
    public ResponseEntity<HistorialResponseDTO>crear(@Valid @RequestBody HistorialRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(historialService.crearHistorial(dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {

        historialService.eliminarHistorial(id);

        return ResponseEntity.ok("Historial eliminado");
    }
    
}
