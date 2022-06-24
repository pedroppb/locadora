package com.example.locadora.service.pessoa.endereco;

        import com.example.locadora.model.entity.pessoa.endereco.Estado;
        import com.example.locadora.model.repository.pessoa.endereco.EstadoRepository;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;
        import java.util.Objects;
        import java.util.Optional;

@Service
public class EstadoService {
    private EstadoRepository repository;

    public EstadoService(EstadoRepository repository) {
        this.repository = repository;
    }

    public List<Estado> getEstados() {
        return repository.findAll();
    }

    public Optional<Estado> getEstadoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Estado salvar(Estado estado) {
        validar(estado);
        return repository.save(estado);
    }

    @Transactional
    public void excluir(Estado estado) {
        Objects.requireNonNull(estado.getId());
        repository.delete(estado);
    }

    public void validar(Estado estado) {

        if(estado.getNome().trim().equals("") || estado.getNome() == null )
        {
            throw new NullPointerException("Estado com Nome inválido");
        }
        if(estado.getPais() == null || estado.getPais().getId() == null || estado.getPais().getId() == 0 )
        {
            throw new NullPointerException("Estado com Pais inválido");
        }
    }
}