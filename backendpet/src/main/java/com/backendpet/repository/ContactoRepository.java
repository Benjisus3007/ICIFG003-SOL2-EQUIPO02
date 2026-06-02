package com.backendpet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backendpet.entity.Contacto;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Integer> {
    
}
