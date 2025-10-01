package com.servicepro.alpha.service;

import com.servicepro.alpha.domain.Professor;
import com.servicepro.alpha.dto.professor.ProfessorDTO;
import com.servicepro.alpha.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    public List<Professor> buscarProfessor() {
        return repository.findAll();
    }

    public Professor buscarProfessorPorMatricula(String matricula) {
        return repository.findByMatricula(matricula);
    }

    public Professor salvarProfessor(ProfessorDTO dto) {
        Professor professor = new Professor();
        professor.setNome(dto.getName());
        professor.setRegisterNumber(dto.getRegisterNumber());
        professor.setEmail(dto.getEmail());
        professor.setPhone(dto.getPhone());
        professor.setStatus(dto.getStatus());
        professor.setStatus(dto.getDepartment());
        professor.setTotalRequests(dto.getTotalRequests());
        professor.setCreatedAt(LocalDate.now());



        return repository.save(professor);
    }

    public Professor atualizarProfessor(String id, ProfessorDTO dto) {
        Optional<Professor> optional = repository.findById(Long.valueOf(id));
        if (optional.isEmpty()) {
            return null;
        }
        Professor professor = optional.get();
        professor.setNome(dto.getName());
        professor.setRegisterNumber(dto.getRegisterNumber());
        professor.setEmail(dto.getEmail());
        professor.setPhone(dto.getPhone());
        professor.setStatus(dto.getStatus());
        professor.setStatus(dto.getDepartment());
        professor.setTotalRequests(professor.getTotalRequests() + dto.getTotalRequests());
        professor.setCreatedAt(LocalDate.now());
        // Atualize outros campos do DTO conforme necessário
        return repository.save(professor);
    }

    public boolean deletarProfessor(String id) {
        Optional<Professor> optional = repository.findById(Long.valueOf(id));
        if (optional.isEmpty()) {
            return false;
        }
        repository.deleteById(Long.valueOf(id));
        return true;
    }
}
