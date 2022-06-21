package com.example.locadora.service.tipoExtra;

        import com.example.locadora.model.entity.aluguel.AdicionalReserva;
        import com.example.locadora.model.repository.aluguel.AdicionalReservaRepository;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;
        import java.util.Objects;
        import java.util.Optional;

@Service
public class AdicionalReservaService {
    private AdicionalReservaRepository repository;

    public AdicionalReservaService(AdicionalReservaRepository repository) {
        this.repository = repository;
    }

    public List<AdicionalReserva> getAdicionaisReserva() {
        return repository.findAll();
    }

    public Optional<AdicionalReserva> getAdicionalReservaById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public AdicionalReserva salvar(AdicionalReserva adicionalReserva) {
        validar(adicionalReserva);
        return repository.save(adicionalReserva);
    }

    @Transactional
    public void excluir(AdicionalReserva adicionalReserva) {
        Objects.requireNonNull(adicionalReserva.getId());
        repository.delete(adicionalReserva);
    }

    public void validar(AdicionalReserva adicionalReserva) {

        if(adicionalReserva.getAdicional() == null || adicionalReserva.getAdicional().getId() == null || adicionalReserva.getAdicional().getId() == 0 )
        {
            throw new NullPointerException("AdicionalReserva com Adicional inválido");
        }
        if(adicionalReserva.getReserva() == null || adicionalReserva.getReserva().getId() == null || adicionalReserva.getReserva().getId() == 0 )
        {
            throw new NullPointerException("AdicionalReserva com Reserva inválido");
        }
        if( adicionalReserva.getValor() <=0 )
        {
            throw new NullPointerException("AdicionalReserva com valor inválido");
        }

        if (adicionalReserva.getId() == 0 || adicionalReserva.getId() == null  ) {
            throw new NullPointerException("AdicionalReserva inválido");
        }
    }
}