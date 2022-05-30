package com.example.locadora.service.pessoa;

import com.example.locadora.model.entity.pessoa.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioService extends JpaRepository<Funcionario, Long> {
}
