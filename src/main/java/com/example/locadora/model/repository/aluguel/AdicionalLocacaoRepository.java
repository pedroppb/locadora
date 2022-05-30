package com.example.locadora.model.repository.aluguel;

import com.example.locadora.model.entity.aluguel.AdicionalLocacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdicionalLocacaoRepository extends JpaRepository<AdicionalLocacao, Long> {
}
