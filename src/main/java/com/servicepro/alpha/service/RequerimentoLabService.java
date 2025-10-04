package com.servicepro.alpha.service;

import com.servicepro.alpha.domain.*;
import com.servicepro.alpha.dto.requerimento.RequerimentoDTO;
import com.servicepro.alpha.dto.requerimento.RequerimentoResponseDTO;
import com.servicepro.alpha.dto.requerimentoLab.RequerimentoLabDTO;
import com.servicepro.alpha.dto.requerimentoLab.RequerimentoLabResponseDTO;
import com.servicepro.alpha.repository.RequerimentolabRepository;
import com.servicepro.alpha.repository.UsuarioRepository;
import com.servicepro.alpha.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequerimentoLabService {

    @Autowired
    private RequerimentolabRepository repository;

    @Autowired
    private UsuarioRepository userRepository;

    public List<RequerimentoLaboratorio> obterTodosRequerimentos() {
        return repository.findAll();
    }

    public RequerimentoLaboratorio buscarRequerimento(Sala sala, String dia, Horario horarioInicial, Horario horarioFinal) {
        return repository.findByLaboratorioReq(sala,horarioInicial,horarioFinal,dia);
    }

    public RequerimentoLaboratorio buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public RequerimentoLaboratorio salvarReq(RequerimentoDTO dto) {
        RequerimentoLaboratorio requerimentolab = new RequerimentoLaboratorio();
        return repository.save(requerimentolab);
    }

    public RequerimentoLaboratorio atualizarReq(String id, RequerimentoLabDTO dto) {
        Optional<RequerimentoLaboratorio> optional = repository.findById(Long.valueOf(id));

        if (optional.isEmpty()) {
            return null;
        }

        RequerimentoLaboratorio requerimento = new RequerimentoLaboratorio();
        requerimento.setNomeDocente(dto.getNomeDocente());
        requerimento.setMatriculaDocente(dto.getMatriculaDocente());
        requerimento.setEmailDocente(dto.getEmailDocente());
        requerimento.setDisciplina(dto.getDisciplina());
        requerimento.setCurso(dto.getCurso());
        requerimento.setUnidadeAcademica(dto.getUnidadeAcademica());
        requerimento.setTituloAula(dto.getTituloAula());
        requerimento.setDia(dto.getDia());
        requerimento.setPresencaTecnicoLaboratorista(dto.getPresencaTecnicoLaboratorista());
        requerimento.setToken(dto.getToken());
        requerimento.setNumeroGruposAlunos(dto.getNumeroGruposAlunos());
        requerimento.setNomeTecnicoLaboratorista(dto.getNomeTecnicoLaboratorista());
        requerimento.setHorarioInicio(dto.getHorarioInicio());
        requerimento.setHorarioFinal(dto.getHorarioFinal());
        requerimento.setLaboratorio(dto.getLaboratorio());
        requerimento.setNumeroAluno(dto.getNumeroAluno());
        requerimento.setUtilitarios(dto.getUtilitarios());
        requerimento.setUpdatedAt(LocalDate.now());


        return repository.save(requerimento);
    }

    public RequerimentoLaboratorio darBaixa(RequerimentoLabDTO dto) {
        RequerimentoLaboratorio requerimento = new RequerimentoLaboratorio();
        requerimento.setNomeDocente(dto.getNomeDocente());
        requerimento.setMatriculaDocente(dto.getMatriculaDocente());
        requerimento.setEmailDocente(dto.getEmailDocente());
        requerimento.setDisciplina(dto.getDisciplina());
        requerimento.setCurso(dto.getCurso());
        requerimento.setUnidadeAcademica(dto.getUnidadeAcademica());
        requerimento.setTituloAula(dto.getTituloAula());
        requerimento.setDia(dto.getDia());
        requerimento.setPresencaTecnicoLaboratorista(dto.getPresencaTecnicoLaboratorista());
        requerimento.setToken(dto.getToken());
        requerimento.setNumeroGruposAlunos(dto.getNumeroGruposAlunos());
        requerimento.setNomeTecnicoLaboratorista(dto.getNomeTecnicoLaboratorista());
        requerimento.setHorarioInicio(dto.getHorarioInicio());
        requerimento.setHorarioFinal(dto.getHorarioFinal());
        requerimento.setLaboratorio(dto.getLaboratorio());
        requerimento.setNumeroAluno(dto.getNumeroAluno());
        requerimento.setUtilitarios(dto.getUtilitarios());
        requerimento.setStatus(dto.getStatus());
        requerimento.setAprovadoPorquem(dto.getAprovadoPorquem());
        requerimento.setRejeitadoPorquem(dto.getRejeitadoPorquem());
        requerimento.setUpdatedAt(LocalDate.now());

        //aqui ficará o envio dos dados para o exchange do rabbitmq e o envio do email é para o adm do laboratorio


        return repository.save(requerimento);
    }

    public RequerimentoLaboratorio buscarToken(String token) {

        return repository.findByToken(token);
    }

    public List<RequerimentoLabResponseDTO> buscandoParaAgenda() {
        return repository.findAll()
                .stream()
                .map(req -> {
                    RequerimentoLabResponseDTO dto = new RequerimentoLabResponseDTO();
                    dto.setId(req.getId());
                    dto.setToken(req.getToken());
                    dto.setLaboratorio(req.getLaboratorio());

                    // usando horário inicial e final
                    dto.setHorarioInicio(req.getHorarioInicio());
                    dto.setHorarioFinal(req.getHorarioFinal());

                    // formatação "HH:mm - HH:mm"
                    if (req.getHorarioInicio() != null && req.getHorarioFinal() != null) {
                        dto.setTime(req.getHorarioInicio().getHorarioInicial() + " - " + req.getHorarioFinal().getHorarioFinal());
                    }

                    // pega o nome do usuário pela matrícula
                    User user = userRepository.findByMatricula(req.getMatriculaDocente());
                    dto.setNomeDocente(user != null ? user.getName() : req.getNomeDocente()); // fallback no nome salvo
                    dto.setMatriculaDocente(req.getMatriculaDocente());
                    dto.setEmailDocente(req.getEmailDocente());

                    dto.setDisciplina(req.getDisciplina());
                    dto.setCurso(req.getCurso());
                    dto.setUnidadeAcademica(req.getUnidadeAcademica());
                    dto.setTituloAula(req.getTituloAula());
                    dto.setDia(req.getDia());
                    dto.setPresencaTecnicoLaboratorista(req.getPresencaTecnicoLaboratorista());
                    dto.setNomeTecnicoLaboratorista(req.getNomeTecnicoLaboratorista());
                    dto.setNumeroGruposAlunos(req.getNumeroGruposAlunos());
                    dto.setStatus(req.getStatus());
                    dto.setAprovadoPorquem(req.getAprovadoPorquem());
                    dto.setRejeitadoPorquem(req.getRejeitadoPorquem());
                    dto.setCreatedAt(req.getCreatedAt());
                    dto.setUpdatedAt(req.getUpdatedAt());
                    try {
                        dto.setNumeroAluno(req.getNumeroAluno() != null ? Integer.valueOf(req.getNumeroAluno()) : null);
                    } catch (NumberFormatException e) {
                        dto.setNumeroAluno(null);
                    }

                    // lista de utilitários
                    dto.setUtilitarios(req.getUtilitarios());

                    return dto;
                })
                .collect(Collectors.toList());
    }

    public boolean delete(String id) {
        Optional<RequerimentoLaboratorio> optional = repository.findById(Long.valueOf(id));
        if (optional.isEmpty()) {
            return false;
        }
        repository.deleteById(Long.valueOf(id));
        return true;
    }

}
