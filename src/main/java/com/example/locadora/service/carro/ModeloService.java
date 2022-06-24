package com.example.locadora.service.carro;

        import com.example.locadora.model.entity.carro.Modelo;
        import com.example.locadora.model.repository.carro.ModeloRepository;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;
        import java.util.Objects;
        import java.util.Optional;

@Service
public class ModeloService {
    private ModeloRepository repository;

    public ModeloService(ModeloRepository repository) {
        this.repository = repository;
    }

    public List<Modelo> getModelos() {
        return repository.findAll();
    }

    public Optional<Modelo> getModeloById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Modelo salvar(Modelo modelo) {
        validar(modelo);
        return repository.save(modelo);
    }

    @Transactional
    public void excluir(Modelo modelo) {
        Objects.requireNonNull(modelo.getId());
        repository.delete(modelo);
    }

    public void validar(Modelo modelo) {

        if(modelo.getNome().trim().equals("") || modelo.getNome() == null )
        {
            throw new NullPointerException("Modelo com Nome inválido");
        }
        if(modelo.getMarcaCarro() == null || modelo.getMarcaCarro().getId() == null || modelo.getMarcaCarro().getId() == 0 )
        {
            throw new NullPointerException("Modelo com Marca inválido");
        }
    }
}