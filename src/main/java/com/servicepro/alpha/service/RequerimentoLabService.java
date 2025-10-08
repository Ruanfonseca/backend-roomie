package com.servicepro.alpha.service;

import com.servicepro.alpha.domain.*;
import com.servicepro.alpha.dto.requerimentoLab.RequerimentoLabDTO;
import com.servicepro.alpha.dto.requerimentoLab.RequerimentoLabResponseDTO;
import com.servicepro.alpha.repository.RequerimentolabRepository;
import com.servicepro.alpha.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.servicepro.alpha.util.TokenGenerator.generateLabToken;

@Service
public class RequerimentoLabService {

    @Autowired
    private RequerimentolabRepository repository;

    @Autowired
    private UsuarioRepository userRepository;

    public List<RequerimentoLaboratorio> obterTodosRequerimentos() {
        return repository.findAll();
    }

    public RequerimentoLaboratorio buscarRequerimento(Laboratorio laboratorio, String dia, Horario horarioInicial, Horario horarioFinal) {
        return repository.findByLaboratorioReq(laboratorio,horarioInicial,horarioFinal,dia);
    }
    public RequerimentoLaboratorio obterPorToken(String token) {
        return repository.findByToken(token);
    }

    public RequerimentoLaboratorio buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public RequerimentoLaboratorio salvarReq(RequerimentoLabDTO dto) {
        RequerimentoLaboratorio requerimentolab = new RequerimentoLaboratorio();
        requerimentolab.setCurso(dto.getCurso());
        requerimentolab.setDia(dto.getDia());
        requerimentolab.setDisciplina(dto.getDisciplina());
        requerimentolab.setEmailDocente(dto.getEmailDocente());
        requerimentolab.setHorarioInicio(dto.getHorarioInicio());
        requerimentolab.setHorarioFinal(dto.getHorarioFinal());
        requerimentolab.setMatriculaDocente(dto.getMatriculaDocente());
        requerimentolab.setNomeDocente(dto.getNomeDocente());
        requerimentolab.setNumeroAluno(dto.getNumeroAluno());
        requerimentolab.setNumeroGruposAlunos(dto.getNumeroGruposAlunos());
        requerimentolab.setPresencaTecnicoLaboratorista(dto.getPresencaTecnicoLaboratorista());
        requerimentolab.setTipoLab(dto.getTipoLab());
        requerimentolab.setTituloAula(dto.getTituloAula());
        requerimentolab.setUnidadeAcademica(dto.getUnidadeAcademica());
        requerimentolab.setUtilitarios(dto.getUtilitarios());
        requerimentolab.setStatus("pending");
        requerimentolab.setToken(generateLabToken());
        requerimentolab.setConfirmacaoLeitura(dto.getConfirmacaoLeitura());
        requerimentolab.setCreatedAt(LocalDate.now());
        return repository.save(requerimentolab);
    }

    public RequerimentoLaboratorio atualizarReq(String id, RequerimentoLabResponseDTO dto) {
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

//    public RequerimentoLaboratorio darBaixa(RequerimentoLabDTO dto) {
//        RequerimentoLaboratorio requerimento = new RequerimentoLaboratorio();
//        requerimento.setNomeDocente(dto.getNomeDocente());
//        requerimento.setMatriculaDocente(dto.getMatriculaDocente());
//        requerimento.setEmailDocente(dto.getEmailDocente());
//        requerimento.setDisciplina(dto.getDisciplina());
//        requerimento.setCurso(dto.getCurso());
//        requerimento.setUnidadeAcademica(dto.getUnidadeAcademica());
//        requerimento.setTituloAula(dto.getTituloAula());
//        requerimento.setDia(dto.getDia());
//        requerimento.setPresencaTecnicoLaboratorista(dto.getPresencaTecnicoLaboratorista());
//        requerimento.setToken(dto.getToken());
//        requerimento.setNumeroGruposAlunos(dto.getNumeroGruposAlunos());
//        requerimento.setNomeTecnicoLaboratorista(dto.getNomeTecnicoLaboratorista());
//        requerimento.setHorarioInicio(dto.getHorarioInicio());
//        requerimento.setHorarioFinal(dto.getHorarioFinal());
//        requerimento.setLaboratorio(dto.getLaboratorio());
//        requerimento.setNumeroAluno(dto.getNumeroAluno());
//        requerimento.setUtilitarios(dto.getUtilitarios());
//        requerimento.setStatus(dto.getStatus());
//        requerimento.setAprovadoPorquem(dto.getAprovadoPorquem());
//        requerimento.setRejeitadoPorquem(dto.getRejeitadoPorquem());
//        requerimento.setUpdatedAt(LocalDate.now());
//
//        //aqui ficará o envio dos dados para o exchange do rabbitmq e o envio do email é para o adm do laboratorio
//
//
//        return repository.save(requerimento);
//    }

    public RequerimentoLaboratorio buscarToken(String token) {

        return repository.findByToken(token);
    }

//    public List<RequerimentoLabResponseDTO> buscandoParaAgenda() {
//        return repository.findAll()
//                .stream()
//                .map(req -> {
//                    RequerimentoLabResponseDTO dto = new RequerimentoLabResponseDTO();
//                    dto.setId(req.getId());
//                    dto.setToken(req.getToken());
//                    dto.setLaboratorio(req.getLaboratorio());
//
//                    // usando horário inicial e final
//                    dto.setHorarioInicio(req.getHorarioInicio());
//                    dto.setHorarioFinal(req.getHorarioFinal());
//
//                    // formatação "HH:mm - HH:mm"
//                    if (req.getHorarioInicio() != null && req.getHorarioFinal() != null) {
//                        dto.setTime(req.getHorarioInicio().getStartTime() + " - " + req.getHorarioFinal().getEndTime());
//                    }
//
//                    // pega o nome do usuário pela matrícula
//                    Usuario user = userRepository.findByMatricula(req.getMatriculaDocente());
//                    dto.setNomeDocente(user != null ? user.getName() : req.getNomeDocente()); // fallback no nome salvo
//                    dto.setMatriculaDocente(req.getMatriculaDocente());
//                    dto.setEmailDocente(req.getEmailDocente());
//
//                    dto.setDisciplina(req.getDisciplina());
//                    dto.setCurso(req.getCurso());
//                    dto.setUnidadeAcademica(req.getUnidadeAcademica());
//                    dto.setTituloAula(req.getTituloAula());
//                    dto.setDia(req.getDia());
//                    dto.setPresencaTecnicoLaboratorista(req.getPresencaTecnicoLaboratorista());
//                    dto.setNomeTecnicoLaboratorista(req.getNomeTecnicoLaboratorista());
//                    dto.setNumeroGruposAlunos(req.getNumeroGruposAlunos());
//                    dto.setStatus(req.getStatus());
//                    dto.setAprovadoPorquem(req.getAprovadoPorquem());
//                    dto.setRejeitadoPorquem(req.getRejeitadoPorquem());
//                    dto.setCreatedAt(req.getCreatedAt());
//                    dto.setUpdatedAt(req.getUpdatedAt());
//                    try {
//                        dto.setNumeroAluno(req.getNumeroAluno() != null ? Integer.valueOf(req.getNumeroAluno()) : null);
//                    } catch (NumberFormatException e) {
//                        dto.setNumeroAluno(null);
//                    }
//
//                    // lista de utilitários
//                    dto.setUtilitarios(req.getUtilitarios());
//
//                    return dto;
//                })
//                .collect(Collectors.toList());
//    }

    public boolean delete(String id) {
        Optional<RequerimentoLaboratorio> optional = repository.findById(Long.valueOf(id));
        if (optional.isEmpty()) {
            return false;
        }
        repository.deleteById(Long.valueOf(id));
        return true;
    }

    public List<RequerimentoLaboratorio> obterPorData(String dataSolicitada) {
        return repository.findByDate(dataSolicitada);
    }
}
