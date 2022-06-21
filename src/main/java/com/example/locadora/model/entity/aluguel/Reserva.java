package com.example.locadora.model.entity.aluguel;



import com.example.locadora.model.entity.carro.Categoria;
import com.example.locadora.model.entity.carro.TipoAluguel;
import com.example.locadora.model.entity.pessoa.Cliente;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Loja lojaRetirada;
    private LocalDateTime dataHoraRetirada;
    @ManyToOne
    private Loja lojaProgramada;
    private LocalDateTime dataHoraProgramada;
    @ManyToOne
    private Categoria categoria;
    @ManyToOne
    private TipoAluguel tipoAluguel;

    private float valor;

}

