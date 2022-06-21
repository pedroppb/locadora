package com.example.locadora.api.dto;

import com.example.locadora.model.entity.carro.TipoAluguel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoAluguelDTO {

    private Long id;
    private String nome;
    private String descricao;

    public  static TipoAluguelDTO create(TipoAluguel tipoAluguel){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(tipoAluguel, TipoAluguelDTO.class);
    }
}
