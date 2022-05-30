package com.example.locadora.model.repository.pessoa;

import com.example.locadora.model.entity.pessoa.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
