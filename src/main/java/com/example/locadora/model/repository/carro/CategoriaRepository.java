package com.example.locadora.model.repository.carro;

import com.example.locadora.model.entity.carro.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
