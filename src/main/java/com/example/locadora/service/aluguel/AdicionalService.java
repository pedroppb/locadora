package com.example.locadora.service.aluguel;

import com.example.locadora.model.entity.aluguel.Adicional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdicionalService extends JpaRepository<Adicional, Long> {
}
