package com.example.locadora.model.entity.pessoa;

import com.example.locadora.model.entity.aluguel.Loja;
import com.example.locadora.model.entity.pessoa.endereco.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String rg;
    private String email;
    @ManyToOne
    private Telefone fixo;
    @ManyToOne
    private Telefone celular;
    @ManyToOne
    private Endereco endereco;
    private String numero;
    private String complemento;
    private String pontoReferencia;
    @ManyToOne
    private Loja loja;
    @ManyToOne
    private Cargo cargo;
    private LocalDate dataAdmissao;
    private LocalDate dataDemissao;
}
