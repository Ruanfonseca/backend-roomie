package com.servicepro.alpha.service;

import com.servicepro.alpha.domain.Horario;
import com.servicepro.alpha.domain.Requerimento;
import com.servicepro.alpha.domain.Sala;
import com.servicepro.alpha.domain.User;
import com.servicepro.alpha.dto.requerimento.RequerimentoDTO;
import com.servicepro.alpha.dto.requerimento.RequerimentoResponseDTO;
import com.servicepro.alpha.dto.sala.SalaDTO;
import com.servicepro.alpha.repository.RequerimentoRepository;
import com.servicepro.alpha.repository.UsuarioRepository;
import com.servicepro.alpha.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequerimentoService {

    @Autowired
    private RequerimentoRepository repository;

    @Autowired
    private UsuarioRepository userRepository;


    public List<Requerimento> obterTodosRequerimentos() {
        return repository.findAll();
    }

    public Requerimento buscarRequerimento(Sala sala, String dia, Horario horarioInicial, Horario horarioFinal) {
        return repository.findBySalaReq(sala,horarioInicial,horarioFinal,dia);
    }

    public Requerimento buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Requerimento salvarReq(RequerimentoDTO dto) {
        Requerimento requerimento = new Requerimento();
        requerimento.setSala(dto.getSala());
        requerimento.setHorarioInicial(dto.getHorarioFinal());
        requerimento.setHorarioFinal(dto.getHorarioFinal());
        requerimento.setDisciplina(dto.getDisciplina());
        requerimento.setNumeroAluno(dto.getNumeroAluno());
        requerimento.setDia(dto.getDia());
        requerimento.setBlocoPreferido(dto.getBlocoPreferido());
        requerimento.setTipoSala(dto.getTipoSala());
        requerimento.setRegistration(dto.getRegistration());
        requerimento.setEquipamentoNecessario(dto.getEquipment());
        requerimento.setObservacoes(dto.getObservacoes());
        requerimento.setStatus(dto.getStatus());
        requerimento.setToken(Util.generateToken());

        return repository.save(requerimento);
    }

    public Requerimento atualizarReq(String id, RequerimentoDTO dto) {
        Optional<Requerimento> optional = repository.findById(Long.valueOf(id));
        if (optional.isEmpty()) {
            return null;
        }

        Requerimento requerimento = new Requerimento();
        requerimento.setSala(dto.getSala());
        requerimento.setHorarioInicial(dto.getHorarioFinal());
        requerimento.setHorarioFinal(dto.getHorarioFinal());
        requerimento.setDisciplina(dto.getDisciplina());
        requerimento.setNumeroAluno(dto.getNumeroAluno());
        requerimento.setDia(dto.getDia());
        requerimento.setBlocoPreferido(dto.getBlocoPreferido());
        requerimento.setTipoSala(dto.getTipoSala());
        requerimento.setRegistration(dto.getRegistration());
        requerimento.setEquipamentoNecessario(dto.getEquipment());
        requerimento.setObservacoes(dto.getObservacoes());
        requerimento.setStatus(dto.getStatus());
        requerimento.setUpdatedAt(LocalDate.now());



        return repository.save(requerimento);
    }

    public Requerimento darBaixa(Requerimento dto) {
        Requerimento requerimento = new Requerimento();
        requerimento.setSala(dto.getSala());
        requerimento.setHorarioInicial(dto.getHorarioFinal());
        requerimento.setHorarioFinal(dto.getHorarioFinal());
        requerimento.setDisciplina(dto.getDisciplina());
        requerimento.setNumeroAluno(dto.getNumeroAluno());
        requerimento.setDia(dto.getDia());
        requerimento.setBlocoPreferido(dto.getBlocoPreferido());
        requerimento.setTipoSala(dto.getTipoSala());
        requerimento.setRegistration(dto.getRegistration());
        requerimento.setEquipamentoNecessario(dto.getEquipamentoNecessario());
        requerimento.setObservacoes(dto.getObservacoes());
        requerimento.setStatus(dto.getStatus());
        requerimento.setAprovadoPorquem(dto.getAprovadoPorquem());
        requerimento.setRejeitadoPorquem(dto.getRejeitadoPorquem());
        requerimento.setUpdatedAt(LocalDate.now());


        //aqui ficará o envio dos dados para o exchange do rabbitmq


        return repository.save(requerimento);
    }

    public Requerimento buscarToken(String token) {

        return repository.findByToken(token);
    }

    public List<RequerimentoResponseDTO> buscandoParaAgenda() {
        return repository.findAll()
                .stream()
                .map(req -> {
                    RequerimentoResponseDTO dto = new RequerimentoResponseDTO();
                    dto.setId(req.getId());
                    dto.setToken(req.getToken());
                    dto.setRoom(req.getSala());

                    // usando horário inicial como schedule
                    dto.setSchedule(req.getHorarioInicial());

                    // pega o nome do usuário pela matrícula
                    User user = userRepository.findByMatricula(req.getRegistration());
                    dto.setProfessorName(user != null ? user.getName() : null);

                    dto.setSubject(req.getDisciplina());

                    try {
                        dto.setNumberOfStudents(req.getNumeroAluno() != null ? Integer.valueOf(req.getNumeroAluno()) : null);
                    } catch (NumberFormatException e) {
                        dto.setNumberOfStudents(null);
                    }

                    dto.setDate(req.getDia());

                    if (req.getHorarioInicial() != null && req.getHorarioFinal() != null) {
                        dto.setTime(req.getHorarioInicial().getHorarioInicial() + " - " + req.getHorarioFinal().getHorarioFinal());
                    }

                    dto.setBlock(req.getBlocoPreferido());
                    dto.setRoomType(req.getTipoSala());
                    dto.setEquipment(req.getEquipamentoNecessario());
                    dto.setObservations(req.getObservacoes());
                    dto.setStatus(req.getStatus());
                    dto.setApprovedBy(req.getAprovadoPorquem());
                    dto.setRejectionReason(req.getRejeitadoPorquem());

                    dto.setCreatedAt(req.getCreatedAt() != null ? req.getCreatedAt().toString() : null);
                    dto.setUpdatedAt(req.getUpdatedAt() != null ? req.getUpdatedAt().toString() : null);

                    return dto;
                })
                .collect(Collectors.toList());
    }

    public boolean delete(String id) {
        Optional<Requerimento> optional = repository.findById(Long.valueOf(id));
        if (optional.isEmpty()) {
            return false;
        }
        repository.deleteById(Long.valueOf(id));
        return true;
    }

}
