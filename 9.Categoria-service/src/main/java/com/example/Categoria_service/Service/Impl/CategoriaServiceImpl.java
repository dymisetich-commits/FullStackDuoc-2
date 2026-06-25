package com.example.Categoria_service.Service.Impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.example.Categoria_service.DTO.CategoriaRequestDTO;
import com.example.Categoria_service.DTO.CategoriaResponseDTO;
import com.example.Categoria_service.Model.CategoriaModel;
import com.example.Categoria_service.Repository.CategoriaRepository;
import com.example.Categoria_service.Service.CategoriaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoriaServiceImpl implements CategoriaService {

     private final CategoriaRepository categoriaRepository;

    // Convertir entidad a DTO
    private CategoriaResponseDTO convertirDTO(
            CategoriaModel categoria) {

        CategoriaResponseDTO dto =
                new CategoriaResponseDTO();

        dto.setId(categoria.getId());
        dto.setNombre(categoria.getNombre());
        dto.setDescripcion(categoria.getDescripcion());

        return dto;
    }

    // Obtener todas
    @Override
    public List<CategoriaResponseDTO> obtenerTodas() {

        log.info("Listando categorias");

        return categoriaRepository.findAll()
                .stream()
                .map(this::convertirDTO)
                .toList();
    }

    // Obtener por ID
    @Override
    public CategoriaResponseDTO obtenerPorId(Long id) {

        log.info("Buscando categoria {}", id);

        CategoriaModel categoria =
                categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Categoria no encontrada"));

        return convertirDTO(categoria);
    }

    // Crear categoria
    @Override
    public CategoriaResponseDTO crearCategoria(
            CategoriaRequestDTO dto) {

        log.info("Creando categoria");

        CategoriaModel categoria =
                new CategoriaModel();

        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());

        CategoriaModel guardada =
                categoriaRepository.save(categoria);

        return convertirDTO(guardada);
    }

    // Actualizar categoria
    @Override
    public CategoriaResponseDTO actualizarCategoria(
            Long id,
            CategoriaRequestDTO dto) {

        log.info("Actualizando categoria {}", id);

        CategoriaModel categoria =
                categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Categoria no encontrada"));

        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());

        CategoriaModel actualizada =
                categoriaRepository.save(categoria);

        return convertirDTO(actualizada);
    }

    // Eliminar categoria
    @Override
    public void eliminarCategoria(Long id) {

        log.info("Eliminando categoria {}", id);

        CategoriaModel categoria =
                categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Categoria no encontrada"));

        categoriaRepository.delete(categoria);
    }
}
