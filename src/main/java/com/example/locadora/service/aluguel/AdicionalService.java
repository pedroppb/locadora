package com.example.locadora.service.aluguel;

        import com.example.locadora.model.entity.aluguel.Adicional;
        import com.example.locadora.model.repository.aluguel.AdicionalRepository;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;
        import java.util.Objects;
        import java.util.Optional;

@Service
public class AdicionalService {
    private AdicionalRepository repository;

    public AdicionalService(AdicionalRepository repository) {
        this.repository = repository;
    }

    public List<Adicional> getAdicionais() {
        return repository.findAll();
    }

    public Optional<Adicional> getAdicionalById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Adicional salvar(Adicional adicional) {
        validar(adicional);
        return repository.save(adicional);
    }

    @Transactional
    public void excluir(Adicional adicional) {
        Objects.requireNonNull(adicional.getId());
        repository.delete(adicional);
    }

    public void validar(Adicional adicional) {

        if(adicional.getNome().trim().equals("") || adicional.getNome() == null )
        {
            throw new NullPointerException("Adicional com Nome inválido");
        }
        if( adicional.getValor() <=0 )
        {
            throw new NullPointerException("Adicional com valor inválido");
        }
    }
}