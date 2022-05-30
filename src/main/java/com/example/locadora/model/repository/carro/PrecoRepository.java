package com.example.locadora.model.repository.carro;

import com.example.locadora.model.entity.carro.Preco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrecoRepository extends JpaRepository<Preco, Long> {
}
