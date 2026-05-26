package com.backendpet.service;

import com.backendpet.entity.CategoriaProducto;
import com.backendpet.repository.CategoriaProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service 
public class CategoriaProductoService {

    @Autowired 
    private CategoriaProductoRepository categoriaRepository;

    public List<CategoriaProducto> obtenerTodas() {
        return categoriaRepository.findAll();
    }
}