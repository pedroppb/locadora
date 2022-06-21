package com.example.locadora.service.carro;

        import com.example.locadora.model.entity.carro.Preco;
        import com.example.locadora.model.repository.carro.PrecoRepository;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;
        import java.util.Objects;
        import java.util.Optional;

@Service
public class PrecoService {
    private PrecoRepository repository;

    public PrecoService(PrecoRepository repository) {
        this.repository = repository;
    }

    public List<Preco> getPrecos() {
        return repository.findAll();
    }

    public Optional<Preco> getPrecoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Preco salvar(Preco preco) {
        validar(preco);
        return repository.save(preco);
    }

    @Transactional
    public void excluir(Preco preco) {
        Objects.requireNonNull(preco.getId());
        repository.delete(preco);
    }

    public void validar(Preco preco) {


        if(preco.getCategoria() == null || preco.getCategoria().getId() == null || preco.getCategoria().getId() == 0 )
        {
            throw new NullPointerException("Preco com Categoria inválido");
        }
        if(preco.getTipoAluguel() == null || preco.getTipoAluguel().getId() == null || preco.getTipoAluguel().getId() == 0 )
        {
            throw new NullPointerException("Preco com TipoAluguel inválido");
        }
        if (preco.getId() == 0 || preco.getId() == null  ) {
            throw new NullPointerException("Preco inválido");
        }
    }
}