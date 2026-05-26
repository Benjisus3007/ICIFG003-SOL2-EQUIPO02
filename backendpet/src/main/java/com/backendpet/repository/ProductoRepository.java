package com.backendpet.repository;

import com.backendpet.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query("SELECT p FROM Producto p WHERE p.categoria.id = :idCategoria")
    List<Producto> buscarPorIdCategoria(@Param("idCategoria") Integer idCategoria);

    @Query(value = "SELECT * FROM producto WHERE stock > 0", nativeQuery = true)
    List<Producto> buscarProductosConStockDisponible();
}