package com.backendpet.service;

import com.backendpet.entity.Producto;
import com.backendpet.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public List<Producto> obtenerPorCategoria(Integer idCategoria) {
        return productoRepository.buscarPorIdCategoria(idCategoria);
    }

    public List<Producto> obtenerConStock() {
        return productoRepository.buscarProductosConStockDisponible();
    }
}