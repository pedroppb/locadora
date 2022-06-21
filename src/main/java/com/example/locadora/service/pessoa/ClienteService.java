package com.example.locadora.service.pessoa;

        import com.example.locadora.model.entity.pessoa.Cliente;
        import com.example.locadora.model.repository.pessoa.ClienteRepository;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

        import java.util.List;
        import java.util.Objects;
        import java.util.Optional;

@Service
public class ClienteService {
    private ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> getClientes() {
        return repository.findAll();
    }

    public Optional<Cliente> getClienteById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {
        validar(cliente);
        return repository.save(cliente);
    }

    @Transactional
    public void excluir(Cliente cliente) {
        Objects.requireNonNull(cliente.getId());
        repository.delete(cliente);
    }

    public void validar(Cliente cliente) {

        if(cliente.getNome().trim().equals("") || cliente.getNome() == null )
        {
            throw new NullPointerException("Cliente com Nome inválido");
        }
        if(cliente.getSenha().trim().equals("") || cliente.getSenha() == null )
        {
            throw new NullPointerException("Cliente com Senha inválido");
        }
        if(cliente.getCpf().trim().equals("") || cliente.getCpf() == null )
        {
            throw new NullPointerException("Cliente com Cpf inválido");
        }
        if(cliente.getRg().trim().equals("") || cliente.getRg() == null )
        {
            throw new NullPointerException("Cliente com Rg inválido");
        }
        if(cliente.getEmail().trim().equals("") || cliente.getEmail() == null )
        {
            throw new NullPointerException("Cliente com Email inválido");
        }
        if(cliente.getEndereco() == null || cliente.getEndereco().getId() == null || cliente.getEndereco().getId() == 0 )
        {
            throw new NullPointerException("Cliente com Endereco inválido");
        }

        if(cliente.getCnh().trim().equals("") || cliente.getCnh() == null )
        {
            throw new NullPointerException("Cliente com cnh inválida");
        }
        if(cliente.getCnhValidade().equals(null) || cliente.getCnhValidade() == null )
        {
            throw new NullPointerException("Cliente com Validade da CNH inválido");
        }


        if(cliente.getCelular() == null || cliente.getCelular().getId() == null || cliente.getCelular().getId() == 0 )
        {
            throw new NullPointerException("Cliente com Celular inválido");
        }



        if(cliente.getTipo() == null )
        {
            throw new NullPointerException("Cliente com Tipo inválido");
        }
        if(cliente.getTipo() == 0){
            if(cliente.getCnpj().trim().equals("") || cliente.getCnpj() == null )
            {
                throw new NullPointerException("Cliente com cnpj inválido");
            }
            if(cliente.getNomeContato().trim().equals("") || cliente.getNomeContato() == null )
            {
                throw new NullPointerException("Cliente com nome do Contato inválido");
            }
            if(cliente.getEmailContato().trim().equals("") || cliente.getEmailContato() == null )
            {
                throw new NullPointerException("Cliente com emailContato inválido");
            }
        }

        if (cliente.getId() == 0 || cliente.getId() == null  ) {
            throw new NullPointerException("cliente inválido");
        }
    }
}