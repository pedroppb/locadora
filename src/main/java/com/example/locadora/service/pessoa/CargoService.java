package com.example.locadora.service.pessoa;

import com.example.locadora.model.entity.pessoa.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoService extends JpaRepository<Cargo, Long> {
}
