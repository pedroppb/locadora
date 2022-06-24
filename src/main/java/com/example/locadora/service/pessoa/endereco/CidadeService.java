package com.example.locadora.service.pessoa.endereco;

        import com.example.locadora.model.entity.pessoa.endereco.Cidade;
        import com.example.locadora.model.repository.pessoa.endereco.CidadeRepository;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;
        import java.util.Objects;
        import java.util.Optional;

@Service
public class CidadeService {
    private CidadeRepository repository;

    public CidadeService(CidadeRepository repository) {
        this.repository = repository;
    }

    public List<Cidade> getCidades() {
        return repository.findAll();
    }

    public Optional<Cidade> getCidadeById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Cidade salvar(Cidade cidade) {
        validar(cidade);
        return repository.save(cidade);
    }

    @Transactional
    public void excluir(Cidade cidade) {
        Objects.requireNonNull(cidade.getId());
        repository.delete(cidade);
    }

    public void validar(Cidade cidade) {

        if(cidade.getNome().trim().equals("") || cidade.getNome() == null )
        {
            throw new NullPointerException("Cidade com Nome inválido");
        }
        if(cidade.getEstado() == null || cidade.getEstado().getId() == null || cidade.getEstado().getId() == 0 )
        {
            throw new NullPointerException("Cidade com Estado inválido");
        }
    }
}