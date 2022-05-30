package com.example.locadora.repository.pessoa.endereco;

import com.example.locadora.model.entity.pessoa.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
