package com.backendpet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendpet.entity.Contacto;
import com.backendpet.service.ContactoService;

@RestController
@RequestMapping("/api/contacto")
@CrossOrigin(origins = "http://localhost:4200")
public class ContactoController {

    @Autowired
    private ContactoService contactoService;

    @PostMapping
    public Contacto enviar (@RequestBody Contacto contacto) {
        return contactoService.guardar(contacto);
    }

    @GetMapping
    public List<Contacto> ListarTodos() {
        return contactoService.obtenerTodos();
    }
    
}
