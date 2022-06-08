package com.example.locadora.api.dto;

import com.example.locadora.model.entity.aluguel.Loja;
import com.example.locadora.model.entity.carro.Carro;
import com.example.locadora.model.entity.carro.Categoria;
import com.example.locadora.model.entity.carro.Modelo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CarroDTO {
    private Long id;
    private String placa;
    private String chassi;
    private long idCategoria;
    private long idLoja;
    private String nomeModelo;
    private String nomeMarca;
    private int anoFabricacao;
    private float odometro;
    private Integer estado;
    private float odometroUltimaManutencao;
    private LocalDate ultimaManutencao;

    public static CarroDTO create(Carro carro){
        ModelMapper modelMapper = new ModelMapper();
        CarroDTO dto = modelMapper.map(carro, CarroDTO.class);

        dto.nomeModelo = carro.getModelo().getNome();
        dto.nomeMarca = carro.getModelo().getMarca().getNome();

        return dto;
    }
}
