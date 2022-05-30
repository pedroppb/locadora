package com.example.locadora.service.carro;

import com.example.locadora.model.entity.carro.Preco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrecoService extends JpaRepository<Preco, Long> {
}
