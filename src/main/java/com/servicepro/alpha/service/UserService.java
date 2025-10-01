package com.servicepro.alpha.service;

import com.servicepro.alpha.domain.User;
import com.servicepro.alpha.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository repository;


    public User findByEmail(String email) {
        return repository.findByEmail(email);

    }
}