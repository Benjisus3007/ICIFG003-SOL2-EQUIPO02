package com.backendpet.repository;

import com.backendpet.entity.CategoriaProducto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, Integer> {

	List<CategoriaProducto> findAll();

}