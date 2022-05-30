package com.example.locadora.model.repository.carro;

import com.example.locadora.model.entity.carro.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeloRepository extends JpaRepository<Modelo, Long> {
}
