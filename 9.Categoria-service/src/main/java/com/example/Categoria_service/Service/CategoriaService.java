package com.example.Categoria_service.Service;

import java.util.List;

import com.example.Categoria_service.DTO.CategoriaRequestDTO;
import com.example.Categoria_service.DTO.CategoriaResponseDTO;

public interface CategoriaService {

    List<CategoriaResponseDTO> obtenerTodas();

    CategoriaResponseDTO obtenerPorId(Long id);

    CategoriaResponseDTO crearCategoria(
            CategoriaRequestDTO dto);

    CategoriaResponseDTO actualizarCategoria(
            Long id,
            CategoriaRequestDTO dto);

    void eliminarCategoria(Long id);
}
