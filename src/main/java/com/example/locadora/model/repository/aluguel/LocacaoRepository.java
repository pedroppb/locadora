package com.example.locadora.model.repository.aluguel;

import com.example.locadora.model.entity.aluguel.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocacaoRepository extends JpaRepository<Locacao, Long> {
}
