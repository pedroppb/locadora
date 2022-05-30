package com.example.locadora.service.aluguel;

import com.example.locadora.model.entity.aluguel.AdicionalLocacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdicionalLocacaoService extends JpaRepository<AdicionalLocacao, Long> {
}
