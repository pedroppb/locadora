package com.example.locadora.service.pessoa.endereco;

import com.example.locadora.model.entity.pessoa.endereco.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoService extends JpaRepository<Estado, Long> {
}
