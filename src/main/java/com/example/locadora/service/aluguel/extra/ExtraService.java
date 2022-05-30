package com.example.locadora.service.aluguel.extra;

import com.example.locadora.model.entity.aluguel.extra.Extra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtraService extends JpaRepository<Extra, Long> {
}
