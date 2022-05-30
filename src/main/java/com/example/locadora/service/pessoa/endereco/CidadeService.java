package com.example.locadora.service.pessoa.endereco;

import com.example.locadora.model.entity.pessoa.endereco.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeService extends JpaRepository<Cidade, Long> {
}
