package com.example.locadora.api.dto;

import com.example.locadora.model.entity.aluguel.Loja;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class LojaDTO {
    private Long id;
    private String nome;
    private String email;
    private String horaAbertura;
    private String horaFechamento;

    private Long idTelefone;

    private Long idEndereco;
    private String numero;
    private String complemento;
    private String pontoReferencia;

    public static LojaDTO create(Loja loja){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(loja, LojaDTO.class);
    }
}
