package com.example.locadora.api.dto;

import com.example.locadora.model.entity.aluguel.extra.Extra;
import org.modelmapper.ModelMapper;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExtraDTO {

    private Long id;
    private String numero;
    private long idTipoExtra;
    private float valor;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;

    public  static ExtraDTO create(Extra extra){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(extra, ExtraDTO.class);
    }
}
