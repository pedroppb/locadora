package com.example.locadora.service.pessoa.endereco;

import com.example.locadora.model.entity.pessoa.endereco.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisService extends JpaRepository<Pais, Long> {
}
