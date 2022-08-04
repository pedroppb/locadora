package com.example.locadora.api.dto;

import com.example.locadora.model.entity.aluguel.extra.Extra;
import org.modelmapper.ModelMapper;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
public class ExtraDTO {

    private Long id;
    private String numero;
    private Long idTipoExtra;
    private Long idLocacao;
    private float valor;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVencimento;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPagamento;


    public  static ExtraDTO create(Extra extra){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(extra, ExtraDTO.class);
    }
}
