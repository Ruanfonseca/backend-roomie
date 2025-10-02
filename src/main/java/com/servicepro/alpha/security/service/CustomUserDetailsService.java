package com.servicepro.alpha.security.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Aqui você pode buscar no banco de dados (JpaRepository)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Mockado para exemplo
        if (email.equals("admin@teste.com")) {
            return User.builder()
                    .username("admin@teste.com")
                    .password("{noop}123456") // {noop} = sem criptografia
                    .roles("ADMIN")
                    .build();
        }
        throw new UsernameNotFoundException("Usuário não encontrado");
    }
}
