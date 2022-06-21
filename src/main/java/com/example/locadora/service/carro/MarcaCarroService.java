package com.example.locadora.service.carro;

        import com.example.locadora.model.entity.carro.MarcaCarro;
        import com.example.locadora.model.repository.carro.MarcaCarroRepository;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;
        import java.util.Objects;
        import java.util.Optional;

@Service
public class MarcaCarroService {
    private MarcaCarroRepository repository;

    public MarcaCarroService(MarcaCarroRepository repository) {
        this.repository = repository;
    }

    public List<MarcaCarro> getMarcasCarro() {
        return repository.findAll();
    }

    public Optional<MarcaCarro> getMarcaCarroById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public MarcaCarro salvar(MarcaCarro marcaCarro) {
        validar(marcaCarro);
        return repository.save(marcaCarro);
    }

    @Transactional
    public void excluir(MarcaCarro marcaCarro) {
        Objects.requireNonNull(marcaCarro.getId());
        repository.delete(marcaCarro);
    }

    public void validar(MarcaCarro marcaCarro) {

        if(marcaCarro.getNome().trim().equals("") || marcaCarro.getNome() == null )
        {
            throw new NullPointerException("MarcaCarro com Nome inválido");
        }

        if (marcaCarro.getId() == 0 || marcaCarro.getId() == null  ) {
            throw new NullPointerException("MarcaCarro inválido");
        }
    }
}