package com.example.locadora.repository.aluguel;

import com.example.locadora.model.entity.aluguel.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
