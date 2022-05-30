package com.example.locadora.service.aluguel;

import com.example.locadora.model.entity.aluguel.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaService extends JpaRepository<Reserva, Long> {
}
