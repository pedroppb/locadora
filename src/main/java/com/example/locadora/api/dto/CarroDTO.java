package com.example.locadora.api.dto;

import com.example.locadora.model.entity.carro.Carro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CarroDTO {
    private Long id;
    private String placa;
    private String chassi;
    private Long idCategoria;
    private Long idLoja;
    private Long idModelo;
    private int anoFabricacao;
    private float odometro;
    private Integer estado;
    private float odometroUltimaManutencao;
    private LocalDate ultimaManutencao;

    public static CarroDTO create(Carro carro){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(carro, CarroDTO.class);
    }
}
