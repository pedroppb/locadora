package com.example.locadora.model.entity.aluguel;

import com.example.locadora.model.entity.pessoa.Telefone;
import com.example.locadora.model.entity.pessoa.endereco.Endereco;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Loja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String horaAbertura;
    private String horaFechamento;
    @ManyToOne
    private Endereco endereco;
    private String numero;
    private String complemento;
    private String pontoReferencia;
    @ManyToOne
    private Telefone telefone;
}

