package com.example.locadora.model.entity.aluguel.extra;

import com.example.locadora.model.entity.aluguel.Locacao;
import com.example.locadora.model.entity.carro.TipoAluguel;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Extra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    @ManyToOne
    private Locacao locacao;
    @ManyToOne
    private TipoExtra tipoExtra;
    private float valor;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
}
