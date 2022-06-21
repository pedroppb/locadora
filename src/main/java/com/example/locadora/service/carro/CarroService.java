package com.example.locadora.service.carro;

        import com.example.locadora.model.entity.carro.Carro;
        import com.example.locadora.model.repository.carro.CarroRepository;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;
        import java.util.Objects;
        import java.util.Optional;

@Service
public class CarroService {
    private CarroRepository repository;

    public CarroService(CarroRepository repository) {
        this.repository = repository;
    }

    public List<Carro> getCarros() {
        return repository.findAll();
    }

    public Optional<Carro> getCarroById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Carro salvar(Carro carro) {
        validar(carro);
        return repository.save(carro);
    }

    @Transactional
    public void excluir(Carro carro) {
        Objects.requireNonNull(carro.getId());
        repository.delete(carro);
    }

    public void validar(Carro carro) {
        if(carro.getPlaca().trim().equals("") || carro.getPlaca() == null )
        {
            throw new NullPointerException("Carro com Placa inválido");
        }
        if(carro.getChassi().trim().equals("") || carro.getChassi() == null )
        {
            throw new NullPointerException("Carro com Chassi inválido");
        }
        if(carro.getAnoFabricacao() <= 0 )
        {
            throw new NullPointerException("Carro com AnoFabricacao inválido");
        }
        if(carro.getOdometro() <= 0 )
        {
            throw new NullPointerException("Carro com Odometro inválido");
        }
        if(carro.getEstado() == null)
        {
            throw new NullPointerException("Carro com Estado inválido");
        }
        if(carro.getModelo() == null || carro.getModelo().getId() == null || carro.getModelo().getId() == 0 )
        {
            throw new NullPointerException("Carro com Modelo inválido");
        }
        if(carro.getCategoria() == null || carro.getCategoria().getId() == null || carro.getCategoria().getId() == 0 )
        {
            throw new NullPointerException("Carro com Categoria inválido");
        }
        if(carro.getLoja() == null || carro.getLoja().getId() == null || carro.getLoja().getId() == 0 )
        {
            throw new NullPointerException("Carro com Loja inválido");
        }
        if (carro.getId() == 0 || carro.getId() == null  ) {
            throw new NullPointerException("Carro inválido");
        }
    }
}