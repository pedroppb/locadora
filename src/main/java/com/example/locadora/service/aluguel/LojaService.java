package com.example.locadora.service.aluguel;

import com.example.locadora.model.entity.aluguel.Loja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LojaService extends JpaRepository<Loja, Long> {
}
