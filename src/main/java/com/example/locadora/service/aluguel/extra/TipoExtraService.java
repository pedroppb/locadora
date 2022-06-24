package com.example.locadora.service.aluguel.extra;

        import com.example.locadora.model.entity.aluguel.extra.TipoExtra;
        import com.example.locadora.model.repository.aluguel.extra.TipoExtraRepository;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;
        import java.util.Objects;
        import java.util.Optional;

@Service
public class TipoExtraService {
    private TipoExtraRepository repository;

    public TipoExtraService(TipoExtraRepository repository) {
        this.repository = repository;
    }

    public List<TipoExtra> getTiposExtra() {
        return repository.findAll();
    }

    public Optional<TipoExtra> getTipoExtraById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public TipoExtra salvar(TipoExtra tipoExtra) {
        validar(tipoExtra);
        return repository.save(tipoExtra);
    }

    @Transactional
    public void excluir(TipoExtra tipoExtra) {
        Objects.requireNonNull(tipoExtra.getId());
        repository.delete(tipoExtra);
    }

    public void validar(TipoExtra tipoExtra) {
        if(tipoExtra.getNome().trim().equals("") || tipoExtra.getNome() == null )
        {
            throw new NullPointerException("TipoExtra com Nome inválido");
        }
        if(tipoExtra.getDescricao().trim().equals("") || tipoExtra.getDescricao () == null )
        {
            throw new NullPointerException("TipoExtra com Descricao inválido");
        }
    }
}