package com.example.locadora.model.repository.pessoa;

import com.example.locadora.model.entity.pessoa.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
}
