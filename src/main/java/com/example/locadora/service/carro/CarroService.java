package com.example.locadora.service.carro;

import com.example.locadora.model.entity.carro.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroService extends JpaRepository<Carro, Long> {
}
