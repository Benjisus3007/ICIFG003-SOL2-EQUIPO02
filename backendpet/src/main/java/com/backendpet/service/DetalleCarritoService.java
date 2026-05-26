package com.backendpet.service;

import com.backendpet.entity.DetalleCarrito;
import com.backendpet.repository.DetalleCarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleCarritoService {

    @Autowired
    private DetalleCarritoRepository detalleCarritoRepository;

    public List<DetalleCarrito> obtenerTodos() {
        return detalleCarritoRepository.findAll();
    }

    public DetalleCarrito obtenerPorId(Integer id) {
        return detalleCarritoRepository.findById(id).orElse(null);
    }

    public DetalleCarrito guardar(DetalleCarrito detalleCarrito) {
        return detalleCarritoRepository.save(detalleCarrito);
    }

    public void eliminar(Integer id) {
        detalleCarritoRepository.deleteById(id);
    }
}