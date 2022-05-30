package com.example.locadora.service.carro;

import com.example.locadora.model.entity.carro.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeloService extends JpaRepository<Modelo, Long> {
}
