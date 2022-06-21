package com.example.locadora.api.dto;

import com.example.locadora.model.entity.aluguel.extra.TipoExtra;
import org.modelmapper.ModelMapper;

public class TipoExtraDTO {
    private Long id;
    private String nome;
    private String descricao;

    public  static TipoAluguelDTO create(TipoExtra tipoExtra){
        ModelMapper modelMapper = new ModelMapper();
        TipoAluguelDTO dto = modelMapper.map(tipoExtra, TipoAluguelDTO.class);

        return dto;
    }
}
