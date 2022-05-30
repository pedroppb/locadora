package com.example.locadora.model.entity.aluguel;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AdicionalReserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Reserva reserva;
    @ManyToOne
    private Adicional adicional;
    private float valor;
}
