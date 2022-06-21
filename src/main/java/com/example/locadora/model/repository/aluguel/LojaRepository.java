package com.example.locadora.model.repository.aluguel;

import com.example.locadora.model.entity.aluguel.Loja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LojaRepository extends JpaRepository<Loja, Long> {
}
