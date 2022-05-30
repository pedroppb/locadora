package com.example.locadora.model.repository.pessoa;

import com.example.locadora.model.entity.pessoa.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
