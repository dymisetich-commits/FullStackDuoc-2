package com.example4.pago_service.Service;

import java.util.List;

import com.example4.pago_service.DTO.PagoRequestDTO;
import com.example4.pago_service.DTO.PagoResponseDTO;

public interface PagoService {


    //Listar todo
    List<PagoResponseDTO> obtenerTodos();

    //listar por id
    PagoResponseDTO obtenerPorId(Long id);

    //crear pago
    PagoResponseDTO crearPago(PagoRequestDTO dto);

    //eliminar pago
    void eliminarPago(Long id);

}
