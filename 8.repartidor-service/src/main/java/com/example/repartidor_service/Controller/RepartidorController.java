package com.example.repartidor_service.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.repartidor_service.DTO.RepartidorRequestDTO;
import com.example.repartidor_service.DTO.RepartidorResponseDTO;
import com.example.repartidor_service.Service.RepartidorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController

@RequestMapping("/api/repartidores")
@RequiredArgsConstructor
public class RepartidorController {

    private final RepartidorService repartidorService;

    // GET todos
    @GetMapping
    public ResponseEntity<List<RepartidorResponseDTO>> listar() {

        return ResponseEntity.ok(repartidorService.obtenerTodos());
    }

    // GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<RepartidorResponseDTO> obtenerPorId(@PathVariable Long id) {

        return ResponseEntity.ok(repartidorService.obtenerPorId(id));
    }

    // GET disponibles
    @GetMapping("/disponibles")
    public ResponseEntity<List<RepartidorResponseDTO>> obtenerDisponibles() {

        return ResponseEntity.ok(repartidorService.obtenerDisponibles());
    }

    // POST
    @PostMapping
    public ResponseEntity<RepartidorResponseDTO> crear(@Valid @RequestBody RepartidorRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(repartidorService.crearRepartidor(dto));
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<RepartidorResponseDTO> actualizar(@PathVariable Long id,@Valid @RequestBody RepartidorRequestDTO dto) {

        return ResponseEntity.ok(repartidorService.actualizarRepartidor( id,dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {

        repartidorService.eliminarRepartidor(id);

        return ResponseEntity.ok("Repartidor eliminado");
    }
}
