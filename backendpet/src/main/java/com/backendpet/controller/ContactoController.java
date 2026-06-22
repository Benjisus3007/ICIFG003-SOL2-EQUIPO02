package com.backendpet.controller;

import com.backendpet.entity.Contacto;
import com.backendpet.repository.ContactoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacto")
@CrossOrigin(origins = "http://localhost:4200") // Permite la conexión con Angular
public class ContactoController {

    private Logger logger = LoggerFactory.getLogger(ContactoController.class);

    @Autowired
    private ContactoRepository contactoRepository;

    @PostMapping
    public Contacto guardarMensajeFrontend(@RequestBody Contacto contacto) {
        logger.info("este es un logger - Recibiendo mensaje de contacto desde Angular");
        return contactoRepository.save(contacto); // Guarda directo en la base de datos
    }
}