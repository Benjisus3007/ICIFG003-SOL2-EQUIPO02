package com.backendpet.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendpet.entity.Contacto;
import com.backendpet.repository.ContactoRepository;

@Service
public class ContactoService {
    
    @Autowired
    private ContactoRepository contactoRepository;

    public Contacto guardar(Contacto contacto) {
        contacto.setFechaEnvio(LocalDateTime.now());
        return contactoRepository.save(contacto);
    }

    public List<Contacto> obtenerTodos() {
        return contactoRepository.findAll();
    }

}
