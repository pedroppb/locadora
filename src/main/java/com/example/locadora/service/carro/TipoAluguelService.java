package com.example.locadora.service.carro;

import com.example.locadora.model.entity.carro.TipoAluguel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoAluguelService extends JpaRepository<TipoAluguel, Long> {
}
