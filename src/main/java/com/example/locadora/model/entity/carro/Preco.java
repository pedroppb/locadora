package com.example.locadora.model.entity.carro;

import javax.persistence.*;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Preco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private TipoAluguel tipoAluguel;
    @ManyToOne
    private Categoria categoria;
    private float valor;
}
