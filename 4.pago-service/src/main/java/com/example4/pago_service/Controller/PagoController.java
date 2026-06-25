package com.example4.pago_service.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example4.pago_service.DTO.PagoRequestDTO;
import com.example4.pago_service.DTO.PagoResponseDTO;
import com.example4.pago_service.Service.PagoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor

public class PagoController {

  private final PagoService pagoService;

  //get todos
  @GetMapping
  public ResponseEntity<List<PagoResponseDTO>> listar(){
    return ResponseEntity.ok(pagoService.obtenerTodos());

  }

  //get por id
  @GetMapping("/{id}")
  public ResponseEntity<PagoResponseDTO> obtenerPorId(@PathVariable Long id){
      return ResponseEntity.ok(pagoService.obtenerPorId(id));
  }

  //Post crear pago
  @PostMapping
  public ResponseEntity<PagoResponseDTO> crear(@Valid @RequestBody PagoRequestDTO dto){

    return ResponseEntity
            .status(HttpStatus.CREATED)
              .body(pagoService.crearPago(dto));
  }

  //eliminar 
  @DeleteMapping("/{id}")
  public ResponseEntity<String> eliminar(@PathVariable Long id){
    pagoService.eliminarPago(id);

    return ResponseEntity.ok("Pago eliminado");
  }

}

/*
CREATE DATABASE pago_db;
*/

