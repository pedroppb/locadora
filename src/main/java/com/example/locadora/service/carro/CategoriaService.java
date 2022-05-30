package com.example.locadora.service.carro;

import com.example.locadora.model.entity.carro.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaService extends JpaRepository<Categoria, Long> {
}
