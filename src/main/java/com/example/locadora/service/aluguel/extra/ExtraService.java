package com.example.locadora.service.aluguel.extra;

        import com.example.locadora.model.entity.aluguel.extra.Extra;
        import com.example.locadora.model.repository.aluguel.extra.ExtraRepository;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;
        import java.util.Objects;
        import java.util.Optional;

@Service
public class ExtraService {
    private ExtraRepository repository;

    public ExtraService(ExtraRepository repository) {
        this.repository = repository;
    }

    public List<Extra> getExtras() {
        return repository.findAll();
    }

    public Optional<Extra> getExtraById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Extra salvar(Extra extra) {
        validar(extra);
        return repository.save(extra);
    }

    @Transactional
    public void excluir(Extra extra) {
        Objects.requireNonNull(extra.getId());
        repository.delete(extra);
    }

    public void validar(Extra extra) {
        if(extra.getTipoExtra() == null || extra.getTipoExtra().getId() == null || extra.getTipoExtra().getId() == 0 )
        {
            throw new NullPointerException("Extra com TipoAluguel inválido");
        }
        if(extra.getLocacao() == null || extra.getLocacao().getId() == null || extra.getLocacao().getId() == 0 )
        {
            throw new NullPointerException("Extra com Locacao inválido");
        }
        if( extra.getValor() <=0 )
        {
            throw new NullPointerException("Extra com Valor inválido");
        }
        if(extra.getDataVencimento() == null)
        {
            throw new NullPointerException("Extra com DataVencimento inválido");
        }
    }
}