package com.example.locadora.service.pessoa.endereco;

import com.example.locadora.model.entity.pessoa.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoService extends JpaRepository<Endereco, Long> {
}
