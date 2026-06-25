package com.example6.historial_service.Exception;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Validaciones
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>>
            manejarValidaciones(
            MethodArgumentNotValidException ex) {

        Map<String, String> errores =
                new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {

                    errores.put(
                            error.getField(),
                            error.getDefaultMessage()
                    );
                });

        return new ResponseEntity<>(
                errores,
                HttpStatus.BAD_REQUEST
        );
    }

   //recurso no encontrado
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> manejarNoEncontrado(
            NoSuchElementException ex) {

        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.NOT_FOUND);
    }


    // Error general
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String>
            manejarGeneral(Exception ex) {

        return new ResponseEntity<>(
                "Error interno del servidor",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
