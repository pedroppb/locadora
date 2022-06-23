package com.example.locadora.model.entity.aluguel;



import com.example.locadora.model.entity.aluguel.extra.Extra;
import com.example.locadora.model.entity.carro.Carro;
import com.example.locadora.model.entity.carro.TipoAluguel;
import com.example.locadora.model.entity.pessoa.Cliente;
import com.example.locadora.model.entity.pessoa.Funcionario;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Locacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Reserva reserva;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Carro carro;
    @ManyToOne
    private Loja lojaRetirada;
    private LocalDateTime dataHoraRetirada;
    @ManyToOne
    private Funcionario  funcionarioRetirada;
    @ManyToOne
    private Loja lojaProgramada;
    private LocalDateTime dataHoraProgramada;
    @ManyToOne
    private Loja lojaDevolucao;
    private LocalDateTime dataHoraDevolucao;

    @ManyToOne
    private Funcionario funcionarioDevolucao;
    @ManyToOne
    private TipoAluguel tipoAluguel;
    private float odometroRetirada;
    private float odometroDevolucao;
    private float valor;

    @ManyToMany
    @JoinTable(name = "extra_locacao",
            joinColumns = @JoinColumn(name = "Locacao_id"),
            inverseJoinColumns = @JoinColumn(name = "Extra_id"))
    private List<Extra> extras;
}
