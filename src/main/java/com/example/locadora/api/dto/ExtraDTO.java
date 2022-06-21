package com.example.locadora.api.dto;
import com.example.locadora.model.entity.aluguel.extra.Extra;
import com.example.locadora.model.entity.aluguel.extra.TipoExtra;
import org.modelmapper.ModelMapper;
import java.time.LocalDate;

public class ExtraDTO {

    private Long id;
    private String numero;
    private TipoExtra tipo;
    private float valor;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;

    public  static ExtraDTO create(Extra extra){
        ModelMapper modelMapper = new ModelMapper();
        ExtraDTO dto = modelMapper.map(extra, ExtraDTO.class);

        return dto;
    }
}
