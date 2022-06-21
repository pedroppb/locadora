package com.example.locadora.model.repository.pessoa.endereco;

import com.example.locadora.model.entity.pessoa.endereco.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
