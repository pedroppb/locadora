package com.example.locadora.service.aluguel;

import com.example.locadora.model.entity.aluguel.AdicionalReserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdicionalReservaService extends JpaRepository<AdicionalReserva, Long> {

}
