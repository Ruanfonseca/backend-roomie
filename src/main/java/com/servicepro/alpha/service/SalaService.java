package com.servicepro.alpha.service;

import com.servicepro.alpha.domain.Professor;
import com.servicepro.alpha.domain.Sala;
import com.servicepro.alpha.dto.sala.SalaDTO;
import com.servicepro.alpha.repository.ProfessorRepository;
import com.servicepro.alpha.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SalaService {
    @Autowired
    private SalaRepository repository;

    public List<Sala> buscarSalas() {
        return repository.findAll();

    }

    public Sala buscarSala(String nome) {
        return repository.findByName(nome);
    }

    public Sala salvarSala(SalaDTO dto) {
        Sala sala = new Sala();
        sala.setNome(dto.getNome());
        sala.setBloco(dto.getBloco());
        sala.setCapacidade(dto.getCapacidade());
        sala.setTipo(dto.getTipo());
        sala.setEquipamento(dto.getEquipamento());
        sala.setAndar(dto.getAndar());
        sala.setStatus(dto.getStatus());
        sala.setDescricao(dto.getDescricao());

        return repository.save(sala);
    }

    public Sala atualizarSala(String id, SalaDTO dto) {
        Optional<Sala> optional = repository.findById(Long.valueOf(id));
        if (optional.isEmpty()) {
            return null;
        }
        Sala sala = optional.get();
        sala.setNome(dto.getNome());
        sala.setBloco(dto.getBloco());
        sala.setCapacidade(dto.getCapacidade());
        sala.setTipo(dto.getTipo());
        sala.setEquipamento(dto.getEquipamento());
        sala.setAndar(dto.getAndar());
        sala.setStatus(dto.getStatus());
        sala.setDescricao(dto.getDescricao());
        // Atualize outros campos do DTO conforme necessário
        return repository.save(sala);
    }

    public boolean deletarSala(String id) {
        Optional<Sala> optional = repository.findById(Long.valueOf(id));
        if (optional.isEmpty()) {
            return false;
        }
        repository.deleteById(Long.valueOf(id));
        return true;
    }
    }

