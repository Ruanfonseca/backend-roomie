package com.servicepro.alpha.service;

import com.servicepro.alpha.domain.*;
import com.servicepro.alpha.dto.requerimento.RequerimentoBaixaDTO;
import com.servicepro.alpha.dto.requerimento.RequerimentoDTO;
import com.servicepro.alpha.dto.requerimento.RequerimentoResponseDTO;
import com.servicepro.alpha.repository.ProfessorRepository;
import com.servicepro.alpha.repository.RequerimentoRepository;
import com.servicepro.alpha.repository.UsuarioRepository;
import com.servicepro.alpha.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.servicepro.alpha.util.TokenGenerator.generateSalToken;

@Service
public class RequerimentoService {

    @Autowired
    private RequerimentoRepository repository;

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private ProfessorRepository profRepository;


    public List<Requerimento> obterTodosRequerimentos() {
        return repository.findAll();
    }

    public Requerimento obterPorToken(String token) {
        return repository.findByToken(token);
    }


    public Requerimento buscarRequerimento(String dia, Horario horarioInicial, Horario horarioFinal) {
        return repository.findByReq(horarioInicial,horarioFinal,dia);
    }

    public Requerimento buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Requerimento salvarReq(RequerimentoDTO dto) {

        Professor teacher = profRepository.findByMatricula(dto.getRegistration());
        if (teacher == null) {
            return null;
        }


        Requerimento requerimento = new Requerimento();
        requerimento.setScheduleInitial(dto.getScheduleInitial());
        requerimento.setScheduleEnd(dto.getScheduleEnd());
        requerimento.setMateria(dto.getMateria());
        requerimento.setNumberOfPeople(dto.getNumberOfPeople());
        requerimento.setDia(dto.getDia());
        requerimento.setRequiredBy(teacher);
        requerimento.setBlockPrefer(dto.getBlockPrefer());
        requerimento.setTypeOfRoom(dto.getTypeOfRoom());
        requerimento.setRegistration(dto.getRegistration());
        requerimento.setEquipament(dto.getEquipament());
        requerimento.setObservations(dto.getObservations());
        requerimento.setStatus(dto.getStatus());
        requerimento.setToken(generateSalToken());
        requerimento.setCreatedAt(LocalDate.now());
        return repository.save(requerimento);
    }

    public Requerimento atualizarReq(String id, RequerimentoDTO dto) {
        Optional<Requerimento> optional = repository.findById(Long.valueOf(id));
        if (optional.isEmpty()) {
            return null;
        }

        Requerimento requerimento = new Requerimento();
        requerimento.setScheduleInitial(dto.getScheduleInitial());
        requerimento.setScheduleEnd(dto.getScheduleEnd());
        requerimento.setMateria(dto.getMateria());
        requerimento.setNumberOfPeople(dto.getNumberOfPeople());
        requerimento.setDia(dto.getDia());
        requerimento.setBlockPrefer(dto.getBlockPrefer());
        requerimento.setTypeOfRoom(dto.getTypeOfRoom());
        requerimento.setRegistration(dto.getRegistration());
        requerimento.setEquipament(dto.getEquipament());
        requerimento.setObservations(dto.getObservations());
        requerimento.setStatus(dto.getStatus());
        requerimento.setUpdatedAt(LocalDate.now());



        return repository.save(requerimento);
    }

    public Requerimento darBaixa(Requerimento dto) {
        Requerimento requerimento = new Requerimento();
        requerimento.setRoom(dto.getRoom());
        requerimento.setScheduleInitial(dto.getScheduleInitial());
        requerimento.setScheduleEnd(dto.getScheduleEnd());
        requerimento.setMateria(dto.getMateria());
        requerimento.setNumberOfPeople(dto.getNumberOfPeople());
        requerimento.setDia(dto.getDia());
        requerimento.setBlockPrefer(dto.getBlockPrefer());
        requerimento.setTypeOfRoom(dto.getTypeOfRoom());
        requerimento.setRegistration(dto.getRegistration());
        requerimento.setEquipament(dto.getEquipament());
        requerimento.setObservations(dto.getObservations());
        requerimento.setStatus(dto.getStatus());
        requerimento.setApprovedBy(dto.getApprovedBy());
        requerimento.setRejectionReason(dto.getRejectionReason());
        requerimento.setUpdatedAt(LocalDate.now());


        //aqui ficará o envio dos dados para o exchange do rabbitmq


        return repository.save(requerimento);
    }

    public Requerimento buscarToken(String token) {

        return repository.findByToken(token);
    }

//    public List<RequerimentoResponseDTO> buscandoParaAgenda() {
//        return repository.findAll()
//                .stream()
//                .map(req -> {
//                    RequerimentoResponseDTO dto = new RequerimentoResponseDTO();
//                    dto.setId(req.getId());
//                    dto.setToken(req.getToken());
//                    dto.setRoom(req.getRoom());
//
//                    // usando horário inicial como schedule
//                    dto.setSchedule(req.get());
//
//                    // pega o nome do usuário pela matrícula
//                    Usuario user = userRepository.findByMatricula(req.getRegistration());
//                    dto.setProfessorName(user != null ? user.getName() : null);
//
//                    dto.setSubject(req.getDisciplina());
//
//                    try {
//                        dto.setNumberOfStudents(req.getNumeroAluno() != null ? Integer.valueOf(req.getNumeroAluno()) : null);
//                    } catch (NumberFormatException e) {
//                        dto.setNumberOfStudents(null);
//                    }
//
//                    dto.setDate(req.getDia());
//
//                    if (req.getHorarioInicial() != null && req.getHorarioFinal() != null) {
//                        dto.setTime(req.getHorarioInicial().getStartTime() + " - " + req.getHorarioFinal().getEndTime());
//                    }
//
//                    dto.setBlock(req.getBlocoPreferido());
//                    dto.setRoomType(req.getTipoSala());
//                    dto.setEquipment(req.getEquipamentoNecessario());
//                    dto.setObservations(req.getObservacoes());
//                    dto.setStatus(req.getStatus());
//                    dto.setApprovedBy(req.getAprovadoPorquem());
//                    dto.setRejectionReason(req.getRejeitadoPorquem());
//
//                    dto.setCreatedAt(req.getCreatedAt() != null ? req.getCreatedAt().toString() : null);
//                    dto.setUpdatedAt(req.getUpdatedAt() != null ? req.getUpdatedAt().toString() : null);
//
//                    return dto;
//                })
//                .collect(Collectors.toList());
//    }

    public boolean delete(String id) {
        Optional<Requerimento> optional = repository.findById(Long.valueOf(id));
        if (optional.isEmpty()) {
            return false;
        }
        repository.deleteById(Long.valueOf(id));
        return true;
    }

    public List<Requerimento> obterPorData(String dataSolicitada) {
        return repository.findByDate(dataSolicitada);
    }
}
