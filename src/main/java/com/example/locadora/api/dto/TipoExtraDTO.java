package com.example.locadora.api.dto;

import com.example.locadora.model.entity.aluguel.extra.TipoExtra;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoExtraDTO {
    private Long id;
    private String nome;
    private String descricao;

    public  static TipoAluguelDTO create(TipoExtra tipoExtra){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(tipoExtra, TipoAluguelDTO.class);
    }
}
