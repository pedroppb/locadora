package com.example.locadora.model.entity.pessoa;

import com.example.locadora.model.entity.aluguel.Locacao;
import com.example.locadora.model.entity.aluguel.Reserva;
import com.example.locadora.model.entity.pessoa.endereco.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String senha;
    private Integer tipo;
    private String cnpj;
    private String nomeContato;
    private String emailContato;
    private String cpf;
    private String rg;
    private String cnh;
    private LocalDate cnhValidade;
    private String email;
    @ManyToOne
    private Telefone fixo;
    @ManyToOne
    private Telefone celular;
    @ManyToOne
    private Telefone outro;
    @ManyToOne
    private Endereco endereco;
    private String numero;
    private String complemento;
    private String pontoReferencia;

    @OneToMany (mappedBy = "cliente")
    private List<Locacao> historicoLocacao;

    @OneToMany (mappedBy = "cliente")
    private List<Reserva> HistoricoReserva;
}
