package com.example.locadora.service.aluguel;

        import com.example.locadora.model.entity.aluguel.Locacao;
        import com.example.locadora.model.repository.aluguel.LocacaoRepository;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;
        import java.util.Objects;
        import java.util.Optional;

@Service
public class LocacaoService {
    private LocacaoRepository repository;

    public LocacaoService(LocacaoRepository repository) {
        this.repository = repository;
    }

    public List<Locacao> getLocacoes() {
        return repository.findAll();
    }

    public Optional<Locacao> getLocacaoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Locacao salvar(Locacao locacao) {
        validar(locacao);
        return repository.save(locacao);
    }

    @Transactional
    public void excluir(Locacao locacao) {
        Objects.requireNonNull(locacao.getId());
        repository.delete(locacao);
    }

    public void validar(Locacao locacao) {
        if(locacao.getCliente() == null || locacao.getCliente().getId() == null || locacao.getCliente().getId() == 0 )
        {
            throw new NullPointerException("Locacao com Cliente inválido");
        }
        if(locacao.getCarro() == null || locacao.getCarro().getId() == null || locacao.getCarro().getId() == 0 )
        {
            throw new NullPointerException("Locacao com Carro inválido");
        }
        if(locacao.getLojaRetirada() == null || locacao.getLojaRetirada().getId() == null || locacao.getLojaRetirada().getId() == 0 )
        {
            throw new NullPointerException("Locacao com LojaRetirada inválido");
        }
        if(locacao.getDataHoraRetirada() == null)
        {
            throw new NullPointerException("Locacao com DataHoraRetirada inválido");
        }
        if(locacao.getFuncionarioRetirada() == null || locacao.getFuncionarioRetirada().getId() == null || locacao.getFuncionarioRetirada().getId() == 0 )
        {
            throw new NullPointerException("Locacao com FuncionarioRetirada inválido");
        }
        if(locacao.getLojaProgramada() == null || locacao.getLojaProgramada().getId() == null || locacao.getLojaProgramada().getId() == 0 )
        {
            throw new NullPointerException("Locacao com LojaProgramada inválido");
        }
        if(locacao.getDataHoraProgramada() == null)
        {
            throw new NullPointerException("Locacao com DataHoraProgramada inválido");
        }
        if(locacao.getTipoAluguel() == null || locacao.getTipoAluguel().getId() == null || locacao.getTipoAluguel().getId() == 0 )
        {
            throw new NullPointerException("Locacao com TipoAluguel inválido");
        }

        if( locacao.getOdometroRetirada() <=0 )
        {
            throw new NullPointerException("Locacao com OdometroRetirada inválido");
        }
        if( locacao.getValor() <=0 )
        {
            throw new NullPointerException("Locacao com valor inválido");
        }
    }
}