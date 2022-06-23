package com.example.locadora.api.dto;

        import com.example.locadora.model.entity.aluguel.AdicionalLocacao;
        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;
        import org.modelmapper.ModelMapper;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AdicionalLocacaoDTO {
    private Long id;
    private Long idAdicional;
    private Long idLocacao;
    private float valor;

    public static AdicionalLocacaoDTO create(AdicionalLocacao adicionalLocacao){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(adicionalLocacao, AdicionalLocacaoDTO.class);
    }
}
