package com.example5.entrega_service.Controller;

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

import com.example5.entrega_service.DTO.EntregaRequestDTO;
import com.example5.entrega_service.DTO.EntregaResponseDTO;
import com.example5.entrega_service.Service.EntregaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/entregas")
public class EntregaController {

        private final EntregaService entregaService;

        //get todas
        @GetMapping
        public ResponseEntity<List<EntregaResponseDTO>> listar(){
                return ResponseEntity.ok(entregaService.obtenerTodas());
        }

        //get por id
        @GetMapping("/{id}")
        public ResponseEntity<EntregaResponseDTO> obtenerPorId(@PathVariable Long id){
                return ResponseEntity.ok(entregaService.obtenerPorId(id));
        }

        //post crearrrrr en el postman
        @PostMapping
        public ResponseEntity<EntregaResponseDTO> crear(@Valid @RequestBody EntregaRequestDTO dto){
                return ResponseEntity.status(HttpStatus.CREATED).body(entregaService.crearEntrega(dto));
        }


        //Delete 
        @DeleteMapping("/{id}")
        public ResponseEntity<String> eliminar(@PathVariable Long id){
                
                entregaService.eliminarEntrega(id);
                
                return ResponseEntity.ok("Entrega eliminada");
        
        }


}
