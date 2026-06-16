package com.backendpet.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backendpet.entity.Contacto;
import com.backendpet.repository.ContactoRepository;

@Service
public class ContactoService {

    private Logger logger = LoggerFactory.getLogger(ContactoService.class);

    @Autowired
    private ContactoRepository contactoRepository;

    public Contacto guardar(Contacto contacto) {
        logger.info("este es un logger - Guardando contacto: " + contacto.getCorreo());
        return contactoRepository.save(contacto);
    }

    public List<Contacto> obtenerTodos() {
        logger.info("este es un logger - Buscando todos los contactos");
        return contactoRepository.findAll();
    }
}