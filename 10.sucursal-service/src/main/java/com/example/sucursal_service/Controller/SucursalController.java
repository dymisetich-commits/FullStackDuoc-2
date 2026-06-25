package com.example.sucursal_service.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.sucursal_service.DTO.SucursalRequestDTO;
import com.example.sucursal_service.DTO.SucursalResponseDTO;
import com.example.sucursal_service.Service.SucursalService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sucursales")
@RequiredArgsConstructor
public class SucursalController {

    private final SucursalService sucursalService;

    // GET todas
    @GetMapping
    public ResponseEntity<List<SucursalResponseDTO>> listar() {

        return ResponseEntity.ok(
                sucursalService.obtenerTodas());
    }

    // GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<SucursalResponseDTO> obtener(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                sucursalService.obtenerPorId(id));
    }

    // POST
    @PostMapping
    public ResponseEntity<SucursalResponseDTO> crear(
            @Valid @RequestBody SucursalRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(sucursalService.crearSucursal(dto));
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<SucursalResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody SucursalRequestDTO dto) {

        return ResponseEntity.ok(
                sucursalService.actualizarSucursal(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @PathVariable Long id) {

        sucursalService.eliminarSucursal(id);

        return ResponseEntity.ok(
                "Sucursal eliminada");
    }
}
