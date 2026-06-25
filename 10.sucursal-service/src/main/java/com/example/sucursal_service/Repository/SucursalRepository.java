package com.example.sucursal_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sucursal_service.Model.SucursalModel;

public interface SucursalRepository extends JpaRepository<SucursalModel, Long> {

}
