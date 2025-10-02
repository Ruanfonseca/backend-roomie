package com.servicepro.alpha.service;

import com.servicepro.alpha.domain.Sala;
import com.servicepro.alpha.domain.User;
import com.servicepro.alpha.dto.sala.SalaDTO;
import com.servicepro.alpha.dto.usuario.UsuarioDTO;
import com.servicepro.alpha.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository repository;


    public User findByEmail(String email) {
        return repository.findByEmail(email);

    }

    public User buscarPorMatricula(String matricula) {
        return repository.findByMatricula(matricula);
    }

    public List<User> buscarUsuarios() {
        return repository.findAll();

    }

    public User salvarUsuario(UsuarioDTO dto) {
        User usuario = new User();
        usuario.setName(dto.getName());
        usuario.setDepartamento(dto.getDepartamento());
        usuario.setEmail(dto.getEmail());
        usuario.setRole(dto.getRole());
        usuario.setPassword(dto.getPassword());
        usuario.setCreatedAt(LocalDate.now());

        return repository.save(usuario);
    }

    public User atualizarUsuario(String id, UsuarioDTO dto) {
        Optional<User> optional = repository.findById(String.valueOf(Long.valueOf(id)));
        if (optional.isEmpty()) {
            return null;
        }
        User usuario = new User();
        usuario.setName(dto.getName());
        usuario.setDepartamento(dto.getDepartamento());
        usuario.setEmail(dto.getEmail());
        usuario.setRole(dto.getRole());
        usuario.setPassword(dto.getPassword());
        usuario.setUpdatedAt(LocalDate.now());
        // Atualize outros campos do DTO conforme necessário
        return repository.save(usuario);
    }

    public boolean deletarUsuario(String id) {
        Optional<User> optional = repository.findById(String.valueOf(Long.valueOf(id)));
        if (optional.isEmpty()) {
            return false;
        }
        repository.deleteById(String.valueOf(Long.valueOf(id)));
        return true;
    }
}