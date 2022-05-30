package com.example.locadora.service.carro;

import com.example.locadora.model.entity.carro.MarcaCarro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaCarroService extends JpaRepository<MarcaCarro, Long> {
}
