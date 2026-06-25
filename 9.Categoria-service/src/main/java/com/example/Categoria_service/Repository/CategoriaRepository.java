package com.example.Categoria_service.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Categoria_service.Model.CategoriaModel;

public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long>  {

}
