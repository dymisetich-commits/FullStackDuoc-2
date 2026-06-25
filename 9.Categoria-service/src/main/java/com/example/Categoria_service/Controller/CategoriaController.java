package com.example.Categoria_service.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Categoria_service.DTO.CategoriaRequestDTO;
import com.example.Categoria_service.DTO.CategoriaResponseDTO;
import com.example.Categoria_service.Service.CategoriaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    // GET todas
    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listar() {

        return ResponseEntity.ok(
                categoriaService.obtenerTodas());
    }

    // GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> obtener(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                categoriaService.obtenerPorId(id));
    }

    // POST
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> crear(
            @Valid @RequestBody CategoriaRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoriaService.crearCategoria(dto));
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaRequestDTO dto) {

        return ResponseEntity.ok(
                categoriaService.actualizarCategoria(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @PathVariable Long id) {

        categoriaService.eliminarCategoria(id);

        return ResponseEntity.ok(
                "Categoria eliminada");
    }
}