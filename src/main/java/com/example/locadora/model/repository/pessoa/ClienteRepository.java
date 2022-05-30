package com.example.locadora.model.repository.pessoa;

import com.example.locadora.model.entity.pessoa.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
