package com.example.restaurante_service.Exception;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Maneja errores globalmente en todos los controllers
@RestControllerAdvice
public class GlobalExceptionHandler {


    // Maneja errores de validación (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarValidaciones(
            MethodArgumentNotValidException ex){

        // Mapa para guardar errores
        Map<String , String> errores = new HashMap<>();

        // Recorre los errores y los guarda
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {

                    errores.put(
                            error.getField(),
                            error.getDefaultMessage()
                    );
                });

        // Retorna error 400
        return new ResponseEntity<>(
                errores,
                HttpStatus.BAD_REQUEST
            );
    }

    // Maneja errores 404
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> manejarNoEncontrado(
            NoSuchElementException ex){

        return new ResponseEntity<>(
                "Recurso no encontrado F",
                HttpStatus.NOT_FOUND
        );
    }

    // Maneja errores generales (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarErroresGenerales(
            Exception ex){

        return new ResponseEntity<>(
                "Error interno del servidor noooooooooooo",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}



