package com.example.locadora.model.entity.carro;

import com.example.locadora.model.entity.aluguel.Loja;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placa;
    private String chassi;
    @ManyToOne
    private Modelo modelo;
    @ManyToOne
    private Categoria categoria;
    @ManyToOne
    private Loja loja;
    private int anoFabricacao;
    private float odometro;
    private Integer estado;
    private float odometroUltimaManutencao;
    private LocalDateTime ultimaManutencao;
}

