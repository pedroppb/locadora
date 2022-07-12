package com.example.locadora.service.pessoa;

//import com.example.scaapi.exception.SenhaInvalidaException;
import com.example.locadora.exception.SenhaInvalidaException;
import com.example.locadora.model.entity.pessoa.Usuario;
import com.example.locadora.model.repository.pessoa.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public Usuario salvar(Usuario usuario){
        return repository.save(usuario);
    }

    public UserDetails autenticar(Usuario usuario){
        UserDetails user = loadUserByUsername(usuario.getLogin());
        boolean senhasBatem = encoder.matches(usuario.getSenha(), user.getPassword());

        if (senhasBatem){
            return user;
        }
        throw new SenhaInvalidaException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        String[] roles;
        switch (usuario.getRoles()){
            case 1:
                roles = new String[]{"FUNC"};
                break;
            case 2:
                roles = new String[]{"FUNC","GEREN"};
                break;
            case 3:
                roles = new String[]{"USER","FUNC","GEREN","ADMIN"};
                break;
            default:
                roles = new String[]{"USER"};
                break;
        }

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }
}
