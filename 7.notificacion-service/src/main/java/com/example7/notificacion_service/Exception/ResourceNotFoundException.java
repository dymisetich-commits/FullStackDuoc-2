package com.example7.notificacion_service.Exception;

public class ResourceNotFoundException  extends RuntimeException{

    public ResourceNotFoundException(String mensaje){
        super(mensaje);
    }

}

