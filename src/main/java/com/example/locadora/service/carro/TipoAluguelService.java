package com.example.locadora.service.carro;

        import com.example.locadora.model.entity.carro.TipoAluguel;
        import com.example.locadora.model.repository.carro.TipoAluguelRepository;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;
        import java.util.Objects;
        import java.util.Optional;

@Service
public class TipoAluguelService {
    private TipoAluguelRepository repository;

    public TipoAluguelService(TipoAluguelRepository repository) {
        this.repository = repository;
    }

    public List<TipoAluguel> getTipoAlugueis() {
        return repository.findAll();
    }

    public Optional<TipoAluguel> geTipoAluguelById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public TipoAluguel salvar(TipoAluguel tipoAluguel) {
        validar(tipoAluguel);
        return repository.save(tipoAluguel);
    }

    @Transactional
    public void excluir(TipoAluguel tipoAluguel) {
        Objects.requireNonNull(tipoAluguel.getId());
        repository.delete(tipoAluguel);
    }

    public void validar(TipoAluguel tipoAluguel) {

        if(tipoAluguel.getNome().trim().equals("") || tipoAluguel.getNome() == null )
        {
            throw new NullPointerException("TipoAluguel com Nome inválido");
        }
        if(tipoAluguel.getDescricao().trim().equals("") || tipoAluguel.getDescricao () == null )
        {
            throw new NullPointerException("TipoAluguel com Descricao inválido");
        }

        if (tipoAluguel.getId() == 0 || tipoAluguel.getId() == null  ) {
            throw new NullPointerException("TipoAluguel inválido");
        }
    }
}