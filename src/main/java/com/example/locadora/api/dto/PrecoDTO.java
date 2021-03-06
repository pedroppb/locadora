package com.example.locadora.api.dto;

import com.example.locadora.model.entity.carro.Preco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PrecoDTO {
    private Long id;
    private Long idTipoAluguel;
    private Long idCategoria;
    private float valor;

    public static PrecoDTO create(Preco preco){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(preco, PrecoDTO.class);
    }
}
