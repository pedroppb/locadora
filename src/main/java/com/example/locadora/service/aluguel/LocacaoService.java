package com.example.locadora.service.aluguel;

import com.example.locadora.model.entity.aluguel.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocacaoService extends JpaRepository<Locacao, Long> {
}
