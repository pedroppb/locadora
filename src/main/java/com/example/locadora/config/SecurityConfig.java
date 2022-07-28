package com.example.locadora.config;

import com.example.locadora.security.JwtAuthFilter;
import com.example.locadora.security.JwtService;
import com.example.locadora.service.pessoa.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private JwtService jwtService;
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, usuarioService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                /*ADICIONAL*/
                	.antMatchers("/api/v1/adicionais/get/id/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/adicionais/get/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/adicionais/post/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/adicionais/put/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/adicionais/delete/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")


                /*ADICIONAL LOCACOES*/
                	.antMatchers("/api/v1/adicionalLocacoes/get/id/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/adicionalLocacoes/get/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/adicionalLocacoes/post/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/adicionalLocacoes/put/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/adicionalLocacoes/delete/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")


                /*ADICIONAL RESERVAS*/
                	.antMatchers("/api/v1/adicionalReservas/get/id/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/adicionalReservas/get/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/adicionalReservas/post/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/adicionalReservas/put/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/adicionalReservas/delete/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")


                /*CARGO*/
                	.antMatchers("/api/v1/cargos/get/id/**")
                		.hasAnyRole("GEREN", "ADMIN")
                	.antMatchers("/api/v1/cargos/get/**")
                		.hasAnyRole("GEREN", "ADMIN")
                	.antMatchers("/api/v1/cargos/post/**")
                		.hasRole("ADMIN")
                	.antMatchers("/api/v1/cargos/put/**")
                		.hasRole("ADMIN")
                	.antMatchers("/api/v1/cargos/delete/**")
                		.hasRole("ADMIN")


                /*CARRO*/
                	.antMatchers("/api/v1/carros/get/id/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/carros/get/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/carros/post/**")
                		.hasRole("ADMIN")
                	.antMatchers("/api/v1/carros/put/**")
                		.hasRole("ADMIN")
                	.antMatchers("/api/v1/carros/delete/**")
                		.hasRole("ADMIN")


                /*CATEGORIA*/
                	.antMatchers("/api/v1/categorias/get/id/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/categorias/get/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/categorias/post/**")
                		.hasRole("ADMIN")
                	.antMatchers("/api/v1/categorias/put/**")
                		.hasRole("ADMIN")
                	.antMatchers("/api/v1/categorias/delete/**")
                		.hasRole("ADMIN")


                /*CIDADE*/
                	.antMatchers("/api/v1/cidades/get/id/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/cidades/get/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/cidades/post/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/cidades/put/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/cidades/delete/**")
                		.hasRole("ADMIN")


                /*CLIENTE*/
                	.antMatchers("/api/v1/clientes/get/id/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/clientes/get/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/clientes/post/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/clientes/put/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/clientes/delete/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")


                /*ENDERECO*/
                	.antMatchers("/api/v1/enderecos/get/id/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/enderecos/get/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/enderecos/post/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/enderecos/put/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/enderecos/delete/**")
                		.hasRole("ADMIN")


                /*ESTADO*/
                	.antMatchers("/api/v1/estados/get/id/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/estados/get/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/estados/post/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/estados/put/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/estados/delete/**")
                		.hasRole("ADMIN")


                /*EXTRA*/
                	.antMatchers("/api/v1/extras/get/id/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/extras/get/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/extras/post/**")
                		.hasAnyRole("FUNC","GEREN", "ADMIN")
                	.antMatchers("/api/v1/extras/put/**")
                		.hasAnyRole("FUNC","GEREN", "ADMIN")
                	.antMatchers("/api/v1/extras/delete/**")
                		.hasAnyRole("FUNC", "GEREN", "ADMIN")


                /*FUNCIONARIO*/
                	.antMatchers("/api/v1/funcionarios/get/id/**")
                		.hasAnyRole("FUNC","GEREN", "ADMIN")
                	.antMatchers("/api/v1/funcionarios/get/**")
                		.hasAnyRole("FUNC","GEREN", "ADMIN")
                	.antMatchers("/api/v1/funcionarios/post/**")
                		.hasAnyRole("ADMIN")
                	.antMatchers("/api/v1/funcionarios/put/**")
                		.hasAnyRole("FUNC", "GEREN","ADMIN")
                	.antMatchers("/api/v1/funcionarios/delete/**")
                		.hasAnyRole("ADMIN")


                /*LOCACAO*/
                	.antMatchers("/api/v1/locacoes/get/id/**")
                		.hasAnyRole("USER","FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/locacoes/get/**")
                		.hasAnyRole("USER","FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/locacoes/post/**")
                		.hasAnyRole("USER", "FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/locacoes/put/**")
                		.hasAnyRole("FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/locacoes/delete/**")
                		.hasAnyRole("FUNC", "GEREN", "ADMIN")


                /*LOJA*/
                	.antMatchers("/api/v1/lojas/get/id/**")
                		.hasAnyRole("USER","FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/lojas/get/**")
                		.hasAnyRole("USER","FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/lojas/post/**")
                		.hasRole("ADMIN")
                	.antMatchers("/api/v1/lojas/put/**")
                		.hasRole("ADMIN")
                	.antMatchers("/api/v1/lojas/delete/**")
                		.hasRole("ADMIN")


                /*MARCA CARRO*/
                	.antMatchers("/api/v1/marcasCarro/get/id/**")
                		.hasAnyRole("FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/marcasCarro/get/**")
                		.hasAnyRole("FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/marcasCarro/post/**")
                		.hasRole("ADMIN")
                	.antMatchers("/api/v1/marcasCarro/put/*")
                		.hasRole("ADMIN")
                	.antMatchers("/api/v1/marcasCarro/delete/**")
                		.hasRole("ADMIN")


                /*MODELO*/
                	.antMatchers("/api/v1/modelos/get/id/**")
                		.hasAnyRole("USER","FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/modelos/get/**")
                		.hasAnyRole("USER","FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/modelos/post/**")
                		.hasRole("ADMIN")
                	.antMatchers("/api/v1/modelos/put/**")
                		.hasRole("ADMIN")
                	.antMatchers("/api/v1/modelos/delete/**")
                		.hasRole("ADMIN")


                /*PAIS*/
                	.antMatchers("/api/v1/paises/get/id/**")
                		.hasAnyRole("USER","FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/paises/get/**")
                		.hasAnyRole("USER","FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/paises/post/**")
                		.hasAnyRole("USER","FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/paises/put/**")
                		.hasAnyRole("FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/paises/delete/**")
                		.hasRole("ADMIN")


                /*PRECO*/
                	.antMatchers("/api/v1/precos/get/id/**")
                		.hasAnyRole("USER","FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/precos/get/**")
                		.hasAnyRole("USER","FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/precos/post/**")
                		.hasRole("ADMIN")
                	.antMatchers("/api/v1/precos/put/**")
                		.hasRole("ADMIN")
                	.antMatchers("/api/v1/precos/delete/**")
                		.hasRole("ADMIN")

                /*RESERVA*/
                	.antMatchers("/api/v1/reservas/get/id/**")
                		.hasAnyRole("USER","FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/reservas/get/**")
                		.hasAnyRole("USER","FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/reservas/post/**")
                		.hasAnyRole("USER","FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/reservas/put/**")
                		.hasAnyRole("USER","FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/reservas/delete/**")
                		.hasAnyRole("USER","FUNC", "GEREN", "ADMIN")


                /*TELEFONE*/
                	.antMatchers("/api/v1/telefones/get/id/**")
                		.hasAnyRole("USER","FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/telefones/get/**")
                		.hasAnyRole("USER","FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/telefones/post/**")
                		.hasAnyRole("USER","FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/telefones/put/**")
                		.hasAnyRole("USER","FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/telefones/delete/**")
                		.hasAnyRole("USER","FUNC", "GEREN", "ADMIN")


                /*TIPO ALUGUEL*/
                	.antMatchers("/api/v1/tiposAluguel/get/id/**")
                		.hasAnyRole("USER","FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/tiposAluguel/get/**")
                		.hasAnyRole("USER","FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/tiposAluguel/post/**")
                		.hasRole("ADMIN")
                	.antMatchers("/api/v1/tiposAluguel/put/**")
                		.hasRole("ADMIN")
                	.antMatchers("/api/v1/tiposAluguel/delete/**")
                		.hasRole("ADMIN")


                /*TIPO EXTRA*/
                	.antMatchers("/api/v1/tiposExtra/get/id/**")
                		.hasAnyRole("USER","FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/tiposExtra/get/**")
                		.hasAnyRole("USER","FUNC", "GEREN", "ADMIN")
                	.antMatchers("/api/v1/tiposExtra/post/**")
                		.hasRole("ADMIN")
                	.antMatchers("/api/v1/tiposExtra/put/**")
                		.hasRole("ADMIN")
                	.antMatchers("/api/v1/tiposExtra/delete/**")
                		.hasRole("ADMIN")

                	.antMatchers(HttpMethod.POST, "/api/v1/usuarios/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        ;
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()	.antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
}