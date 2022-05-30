package com.example.locadora.service.pessoa;

import com.example.locadora.model.entity.pessoa.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteService extends JpaRepository<Cliente, Long> {
}
