package com.example.locadora.model.repository.aluguel;

import com.example.locadora.model.entity.aluguel.Adicional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdicionalRepository extends JpaRepository<Adicional, Long> {
}
